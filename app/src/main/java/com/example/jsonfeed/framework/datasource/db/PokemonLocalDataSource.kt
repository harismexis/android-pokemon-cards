package com.example.jsonfeed.framework.datasource.db

import com.example.jsonfeed.data.LocalDataSource
import com.example.jsonfeed.domain.Item
import com.example.jsonfeed.framework.extensions.toItem
import com.example.jsonfeed.framework.extensions.toItems
import com.example.jsonfeed.framework.extensions.toPokemonEntities

import javax.inject.Inject

class PokemonLocalDataSource @Inject constructor(
    private val dao: PokemonLocalDao
) : LocalDataSource {

    override suspend fun insert(items: List<Item>) {
        dao.insertItems(items.toPokemonEntities())
    }

    override suspend fun getItem(itemId: String): Item? {
        val entity = dao.getItemById(itemId)
        entity?.let {
            return it.toItem()
        }
        return null
    }

    override suspend fun getAll(): List<Item> {
        return dao.getAllItems().toItems()
    }

}