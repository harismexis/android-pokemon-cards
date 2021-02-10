package com.example.jsonfeed.data

import com.example.jsonfeed.domain.RemoteFeed

interface RemoteFeedDataSource {

    suspend fun getFeedData(): RemoteFeed?

}