package com.example.jsonfeed.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.jsonfeed.api.PokemonApi
import com.example.jsonfeed.db.PokemonDatabase
import com.example.jsonfeed.db.RemoteKeys
import com.example.jsonfeed.model.PokemonItem
import retrofit2.HttpException
import java.io.IOException

private const val POKEMON_STARTING_PAGE_INDEX = 1

@OptIn(ExperimentalPagingApi::class)
class PokemonRemoteMediator(
    private val api: PokemonApi,
    private val database: PokemonDatabase
) : RemoteMediator<Int, PokemonItem>() {

    override suspend fun initialize(): InitializeAction {
        return InitializeAction.LAUNCH_INITIAL_REFRESH
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, PokemonItem>)
    : MediatorResult {

        val page = when (loadType) {
            LoadType.REFRESH -> {
                val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                remoteKeys?.nextKey?.minus(1) ?: POKEMON_STARTING_PAGE_INDEX
            }
            LoadType.PREPEND -> {
                val remoteKeys = getRemoteKeyForFirstItem(state)
                val prevKey = remoteKeys?.prevKey ?: return MediatorResult.Success(endOfPaginationReached = false)
                prevKey
            }
            LoadType.APPEND -> {
                val remoteKeys = getRemoteKeyForLastItem(state)
                val nextKey = remoteKeys?.nextKey ?: return MediatorResult.Success(endOfPaginationReached = false)
                nextKey
            }
        }

        try {
            val apiResponse = api.getPokemonCards(page, state.config.pageSize)
            val pokemons = apiResponse.cards
            val endOfPaginationReached = pokemons.isEmpty()
            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    database.getRemoteKeysDao().clearRemoteKeys()
                    database.getPokemonDao().deleteAll()
                }
                val prevKey = if (page == POKEMON_STARTING_PAGE_INDEX) null else page - 1
                val nextKey = if (endOfPaginationReached) null else page + 1
                val keys = pokemons.map {
                    RemoteKeys(pokemonId = it.id, prevKey = prevKey, nextKey = nextKey)
                }
                database.getRemoteKeysDao().insertAll(keys)
                database.getPokemonDao().insertItems(pokemons)
            }
            return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (exception: IOException) {
            return MediatorResult.Error(exception)
        } catch (exception: HttpException) {
            return MediatorResult.Error(exception)
        }
    }

    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, PokemonItem>)
    : RemoteKeys? {
        return state.pages.lastOrNull() { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { pokemon ->
                database.getRemoteKeysDao().getRemoteKeysForPokemonId(pokemon.id)
            }
    }

    private suspend fun getRemoteKeyForFirstItem(
        state: PagingState<Int, PokemonItem>)
    : RemoteKeys? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { pokemon ->
                database.getRemoteKeysDao().getRemoteKeysForPokemonId(pokemon.id)
            }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, PokemonItem>
    ): RemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { pokemonId ->
                database.getRemoteKeysDao().getRemoteKeysForPokemonId(pokemonId)
            }
        }
    }
}
