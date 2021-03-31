package com.harismexis.pokemon.framework.datasource.db

import com.harismexis.pokemon.data.LocalDataSource
import com.harismexis.pokemon.domain.Item
import com.harismexis.pokemon.framework.extensions.toItem
import com.harismexis.pokemon.framework.extensions.toItems
import com.harismexis.pokemon.framework.extensions.toPokemonEntities
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