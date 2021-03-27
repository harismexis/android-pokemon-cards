package com.example.jsonfeed.repository

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.jsonfeed.api.FeedApi
import com.example.jsonfeed.datamodel.PokemonItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PokemonRemoteRepository @Inject constructor(private val api: FeedApi) {

    fun getPokemonCardsStream(): Flow<PagingData<PokemonItem>> {
        Log.d("PokemonRepository", "New call")
        return Pager(
            config = PagingConfig(pageSize = NETWORK_PAGE_SIZE, enablePlaceholders = false),
            pagingSourceFactory = { PokemonPagingSource(api) }
        ).flow
    }

    companion object {
        const val NETWORK_PAGE_SIZE = 50
    }
}
