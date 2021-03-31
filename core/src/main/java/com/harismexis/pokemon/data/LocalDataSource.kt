package com.harismexis.pokemon.data

import com.harismexis.pokemon.domain.Item

interface LocalDataSource {

    suspend fun insert(items: List<Item>)

    suspend fun getItem(itemId: String): Item?

    suspend fun getAll(): List<Item>?
}