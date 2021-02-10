package com.example.jsonfeed.framework.datasource.network

import com.example.jsonfeed.data.RemoteDataSource
import com.example.jsonfeed.domain.Item

import javax.inject.Inject

class NetworkDataSource @Inject constructor(
    private val dao: RetrofitDao
) : RemoteDataSource {

    override suspend fun getItems(): List<Item>? {
        val items = mutableListOf<Item>()
        val feed = dao.getPokemonCards() ?: return null
        val remoteItems = feed.cards ?: return null
        val validItems = remoteItems.filter { it != null && !it.id.isNullOrBlank() }
        items.addAll(validItems.map {
            Item(
                it!!.id!!,
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
        })
        return items.toList()
    }
}