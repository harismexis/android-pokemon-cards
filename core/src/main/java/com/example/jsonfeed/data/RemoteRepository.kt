package com.example.jsonfeed.data

import com.example.jsonfeed.domain.RemoteFeed

data class RemoteRepository(
    private val dataSource: RemoteDataSource
) {
    suspend fun getFeed(): RemoteFeed? = dataSource.getFeedData()
}