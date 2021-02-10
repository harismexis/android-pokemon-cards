package com.example.jsonfeed.framework.extensions

import com.example.jsonfeed.domain.Item
import com.example.jsonfeed.framework.datasource.db.PokemonEntity

fun List<PokemonEntity?>?.toItems(): List<Item>? {
    if (this == null) return null
    val filteredList = this.filterNotNull()
    val items = mutableListOf<Item>()
    items.addAll(filteredList.map {
        it.toItem()
    })
    return items.toList()
}

fun PokemonEntity.toItem(): Item {
    return Item(
        this.id,
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

fun Item.toPokemonEntity(): PokemonEntity {
    return PokemonEntity(
        this.id,
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
