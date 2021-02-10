package com.example.jsonfeed.interactors

import com.example.jsonfeed.data.LocalFeedRepository
import com.example.jsonfeed.domain.LocalFeedItem

class InsertLocalFeedItems(
    private val localFeedRepository: LocalFeedRepository
) {
    suspend operator fun invoke(items: List<LocalFeedItem>) = localFeedRepository.insertItems(items)
}
