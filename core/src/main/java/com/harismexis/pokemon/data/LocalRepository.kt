package com.harismexis.pokemon.data

import com.harismexis.pokemon.domain.Item

class LocalRepository(
    private val dataSource: LocalDataSource
) {
    suspend fun insertItems(items: List<Item>) = dataSource.insert(items)

    suspend fun getItem(itemId: String): Item? = dataSource.getItem(itemId)

    suspend fun getItems(): List<Item>? = dataSource.getAll()
}