package com.harismexis.pokemon.data

import com.harismexis.pokemon.domain.Item

interface RemoteDataSource {

    suspend fun getItems(): List<Item>?

}