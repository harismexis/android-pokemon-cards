package com.example.jsonfeed.interactors

import com.example.jsonfeed.data.RemoteFeedRepository

class GetRemoteFeed(
    private val remoteFeedRepository: RemoteFeedRepository
) {
    suspend operator fun invoke() = remoteFeedRepository.getFeed()
}
