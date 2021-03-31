package com.harismexis.pokemon.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.harismexis.pokemon.api.PokemonApi
import com.harismexis.pokemon.datamodel.remote.PokemonItem
import com.harismexis.pokemon.repository.PokemonRemoteRepository.Companion.NETWORK_PAGE_SIZE
import retrofit2.HttpException
import java.io.IOException

private const val GITHUB_STARTING_PAGE_INDEX = 1

class PokemonPagingSource(private val service: PokemonApi) : PagingSource<Int, PokemonItem>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PokemonItem> {
        val page = params.key ?: GITHUB_STARTING_PAGE_INDEX
        return try {
            val response = service.getPokemonCards(page, params.loadSize)
            val cards = response.cards
            val nextKey = if (cards.isEmpty()) {
                null
            } else {
                page + (params.loadSize / NETWORK_PAGE_SIZE)
            }
            LoadResult.Page(
                data = cards,
                prevKey = if (page == GITHUB_STARTING_PAGE_INDEX) null else page - 1,
                nextKey = nextKey
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, PokemonItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}
