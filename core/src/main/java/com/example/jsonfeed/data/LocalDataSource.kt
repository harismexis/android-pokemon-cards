package com.example.jsonfeed.data

import com.example.jsonfeed.domain.LocalItem

interface LocalDataSource {

    suspend fun insert(items: List<LocalItem>)

    suspend fun getItem(itemId: String): LocalItem?

    suspend fun getAll(): List<LocalItem>?
}