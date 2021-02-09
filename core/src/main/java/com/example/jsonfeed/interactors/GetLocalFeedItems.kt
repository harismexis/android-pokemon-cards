package com.example.jsonfeed.interactors

import com.example.jsonfeed.data.LocalFeedRepository

class GetLocalFeedItems(private val localFeedRepository: LocalFeedRepository) {
    suspend operator fun invoke() = localFeedRepository.getItems()
}
