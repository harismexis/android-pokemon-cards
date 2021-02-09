package com.example.jsonfeed.data

import com.example.jsonfeed.domain.FeedData

data class RemoteFeedRepository(
    private val remoteFeedDataSource: RemoteFeedDataSource
) {
    suspend fun getFeed(): FeedData? = remoteFeedDataSource.getFeedData()
}