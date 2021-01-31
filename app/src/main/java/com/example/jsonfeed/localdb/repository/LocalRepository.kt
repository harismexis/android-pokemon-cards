package com.example.jsonfeed.localdb.repository

import com.example.jsonfeed.localdb.LocalDao
import com.example.jsonfeed.localdb.LocalItem

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalRepository @Inject constructor(val dao: LocalDao) {

    suspend fun insertItems(items: List<LocalItem>) {
        dao.deleteAll()
        dao.insertItems(items)
    }

    suspend fun getItemById(itemId: String): LocalItem? {
        return dao.getItemById(itemId)
    }

    suspend fun getAllItems(): List<LocalItem>? {
        return dao.getAllItems()
    }

}
