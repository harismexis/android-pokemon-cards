package com.harismexis.pokemon.framework.datasource.network

import com.harismexis.pokemon.framework.extensions.toItems
import com.harismexis.pokemon.data.RemoteDataSource
import com.harismexis.pokemon.domain.Item
import javax.inject.Inject

class PokemonRemoteDataSource @Inject constructor(
    private val dao: PokemonRemoteDao
) : RemoteDataSource {

    override suspend fun getItems(): List<Item> {
        return dao.getPokemonCards().toItems()
    }

}