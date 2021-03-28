package com.example.jsonfeed.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.jsonfeed.api.PokemonApi
import com.example.jsonfeed.db.PokemonDatabase
import com.example.jsonfeed.model.PokemonItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PokemonRepository @Inject constructor(
    private val api: PokemonApi,
    private val database: PokemonDatabase) {

    fun getPokemonCardsStream(): Flow<PagingData<PokemonItem>> {

        val pagingSourceFactory = { database.getPokemonDao().getAllItems() }

        @OptIn(ExperimentalPagingApi::class)
        return Pager(
            config = PagingConfig(pageSize = NETWORK_PAGE_SIZE, enablePlaceholders = false),
            remoteMediator = PokemonRemoteMediator(
                api,
                database
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

    companion object {
        const val NETWORK_PAGE_SIZE = 50
    }
}
