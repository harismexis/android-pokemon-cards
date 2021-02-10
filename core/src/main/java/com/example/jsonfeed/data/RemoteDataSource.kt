package com.example.jsonfeed.data

import com.example.jsonfeed.domain.RemoteFeed

interface RemoteDataSource {

    suspend fun getFeedData(): RemoteFeed?

}