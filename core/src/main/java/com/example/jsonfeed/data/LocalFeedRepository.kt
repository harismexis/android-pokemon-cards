package com.example.jsonfeed.data

import com.example.jsonfeed.domain.LocalFeedItem

class LocalFeedRepository(
    private val localFeedDataSource: LocalFeedDataSource
) {
    suspend fun insertItems(items: List<LocalFeedItem>) = localFeedDataSource.insert(items)

    suspend fun getItem(itemId: String): LocalFeedItem? = localFeedDataSource.getItem(itemId)

    suspend fun getItems(): List<LocalFeedItem>? = localFeedDataSource.getAll()
}