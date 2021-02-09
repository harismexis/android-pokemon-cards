package com.example.jsonfeed.interactors

import com.example.jsonfeed.data.LocalFeedRepository

class GetLocalFeedItem(private val localFeedRepository: LocalFeedRepository) {
    suspend operator fun invoke(itemId: String) = localFeedRepository.getItem(itemId)
}
