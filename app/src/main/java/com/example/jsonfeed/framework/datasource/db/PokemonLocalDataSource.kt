package com.example.jsonfeed.framework.datasource.db

import com.example.jsonfeed.data.LocalDataSource
import com.example.jsonfeed.domain.Item

import javax.inject.Inject

class PokemonLocalDataSource @Inject constructor(
    private val dao: PokemonLocalDao
) : LocalDataSource {

    override suspend fun insert(items: List<Item>) {
        val entities = mutableListOf<PokemonEntity>()
        for (item in items) {
            entities.add(
                PokemonEntity(
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
        dao.insertItems(entities.toList())
    }

    override suspend fun getItem(itemId: String): Item? {
        val entity = dao.getItemById(itemId)
        entity?.let {
            return Item(
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

    override suspend fun getAll(): List<Item>? {
        val entities = dao.getAllItems()
        entities?.let { list ->
            return list.map {
                Item(
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