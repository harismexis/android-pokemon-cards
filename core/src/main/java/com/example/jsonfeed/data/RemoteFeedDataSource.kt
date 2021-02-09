package com.example.jsonfeed.data

import com.example.jsonfeed.domain.FeedData

interface RemoteFeedDataSource {

    suspend fun getFeedData(): FeedData?

}