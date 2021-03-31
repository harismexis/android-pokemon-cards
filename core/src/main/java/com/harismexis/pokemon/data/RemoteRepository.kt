package com.harismexis.pokemon.data

import com.harismexis.pokemon.domain.Item

data class RemoteRepository(
    private val dataSource: RemoteDataSource
) {
    suspend fun getItems(): List<Item>? = dataSource.getItems()
}