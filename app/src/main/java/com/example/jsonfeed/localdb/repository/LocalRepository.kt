package com.example.jsonfeed.localdb.repository

import com.example.jsonfeed.localdb.LocalDao
import com.example.jsonfeed.localdb.LocalFeedItem

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalRepository @Inject constructor(val dao: LocalDao) {

    suspend fun insertFeedItems(items: List<LocalFeedItem>) {
        dao.deleteAll()
        dao.insertItems(items)
    }

    suspend fun getFeedItemById(itemId: String): LocalFeedItem? {
        return dao.getFeedItemById(itemId)
    }

    suspend fun getFeedItems(): List<LocalFeedItem>? {
        return dao.getFeedItems()
    }

}
