package com.example.jsonfeed.framework.datasource.db

import com.example.jsonfeed.data.LocalDataSource
import com.example.jsonfeed.domain.LocalItem
// import com.google.common.collect.ImmutableList
import javax.inject.Inject

class LocalStorageDataSource @Inject constructor(
    private val dao: RoomDao
) : LocalDataSource {

    override suspend fun insert(items: List<LocalItem>) {
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
        dao.insertItems(entities)
    }

    override suspend fun getItem(itemId: String): LocalItem? {
        val entity = dao.getItemById(itemId)
        entity?.let {
            return LocalItem(
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

    override suspend fun getAll(): List<LocalItem>? {
        val entities = dao.getAllItems()
        entities?.let { list ->
            return list.map {
                LocalItem(
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