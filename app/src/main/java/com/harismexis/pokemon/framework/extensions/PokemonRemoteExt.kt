package com.harismexis.pokemon.framework.extensions

import com.harismexis.pokemon.domain.Item
import com.harismexis.pokemon.framework.datasource.network.PokemonCard
import com.harismexis.pokemon.framework.datasource.network.PokemonFeed

fun PokemonFeed?.toItems(): List<Item> {
    val items = mutableListOf<Item>()
    if (this == null) return items.toList()
    val remoteItems = this.cards ?: return items.toList()
    val filteredList = remoteItems.filter { it != null && !it.id.isNullOrBlank() }
    items.addAll(filteredList.map {
        it!!.toItem(it.id!!)
    })
    return items.toList()
}

private fun PokemonCard.toItem(id: String): Item {
    return Item(
        id,
        this.name,
        this.imageUrl,
        this.imageUrlHiRes,
        this.supertype,
        this.subtype,
        this.artist,
        this.rarity,
        this.series,
        this.set,
        this.setCode
    )
}
