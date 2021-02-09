package com.example.jsonfeed.data

import com.example.jsonfeed.domain.LocalFeedItem

interface LocalFeedDataSource {

    suspend fun insert(items: List<LocalFeedItem>)

    suspend fun getItem(itemId: String): LocalFeedItem?

    suspend fun getAll(): List<LocalFeedItem>?
}