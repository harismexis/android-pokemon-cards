package com.example.jsonfeed.framework.db

import com.example.jsonfeed.data.LocalFeedDataSource
import com.example.jsonfeed.domain.LocalFeedItem
// import com.google.common.collect.ImmutableList
import javax.inject.Inject

class RoomLocalFeedDataSource @Inject constructor(
    val localDao: LocalDao
) : LocalFeedDataSource {

    override suspend fun insert(items: List<LocalFeedItem>) {
        val entities = mutableListOf<LocalItemEntity>()
        for (item in items) {
            entities.add(
                LocalItemEntity(
                    item.id,
                    item.name,
                    item.imageUrl,
                    item.imageUrlHiRes,
                    item.supertype,
                    item.subtype,
                    item.artist,
                    item.rarity,
                    item.series,
                    item.set,
                    item.setCode
                )
            )
        }
        // localDao.insertItems(ImmutableList.copyOf(entities))
        localDao.insertItems(entities)
    }

    override suspend fun getItem(itemId: String): LocalFeedItem? {
        val entity = localDao.getItemById(itemId)
        entity?.let {
            return LocalFeedItem(
                entity.id,
                entity.name,
                entity.imageUrl,
                entity.imageUrlHiRes,
                entity.supertype,
                entity.subtype,
                entity.artist,
                entity.rarity,
                entity.series,
                entity.set,
                entity.setCode
            )
        }
        return null
    }

    override suspend fun getAll(): List<LocalFeedItem>? {
        val entities = localDao.getAllItems()
        entities?.let { list ->
            return list.map {
                LocalFeedItem(
                    it.id,
                    it.name,
                    it.imageUrl,
                    it.imageUrlHiRes,
                    it.supertype,
                    it.subtype,
                    it.artist,
                    it.rarity,
                    it.series,
                    it.set,
                    it.setCode
                )
            }
        }
        return null
    }

}