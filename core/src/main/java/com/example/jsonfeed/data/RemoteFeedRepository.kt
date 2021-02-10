package com.example.jsonfeed.data

import com.example.jsonfeed.domain.RemoteFeed

data class RemoteFeedRepository(
    private val remoteFeedDataSource: RemoteFeedDataSource
) {
    suspend fun getFeed(): RemoteFeed? = remoteFeedDataSource.getFeedData()
}