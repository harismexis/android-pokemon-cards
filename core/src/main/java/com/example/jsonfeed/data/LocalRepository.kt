package com.example.jsonfeed.data

import com.example.jsonfeed.domain.LocalItem

class LocalRepository(
    private val dataSource: LocalDataSource
) {
    suspend fun insertItems(items: List<LocalItem>) = dataSource.insert(items)

    suspend fun getItem(itemId: String): LocalItem? = dataSource.getItem(itemId)

    suspend fun getItems(): List<LocalItem>? = dataSource.getAll()
}