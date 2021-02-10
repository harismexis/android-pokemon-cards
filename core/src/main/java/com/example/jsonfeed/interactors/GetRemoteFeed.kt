package com.example.jsonfeed.interactors

import com.example.jsonfeed.data.RemoteRepository

class GetRemoteFeed(
    private val repository: RemoteRepository
) {
    suspend operator fun invoke() = repository.getFeed()
}
