package com.example.jsonfeed.framework.extensions

import com.example.jsonfeed.framework.datasource.network.PokemonFeed
import com.example.jsonfeed.framework.datasource.network.PokemonCard
import com.example.jsonfeed.domain.Item

//fun PokemonFeed?.toLocalItems(): List<Item> {
//    val items = mutableListOf<Item>()
//    if (this == null) return localItems
//    this.cards?.let { cards ->
//        for (item in cards) {
//            item?.let { currentItem ->
//                currentItem.id?.let { id ->
//                    if (id.isNotBlank()) {
//                        val localItem = currentItem.toLocalItem(id)
//                        localItems.add(localItem)
//                    }
//                }
//            }
//        }
//    }
//    return localItems
//}

private fun PokemonCard.toLocalItem(id: String): Item {
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
