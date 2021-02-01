package com.example.jsonfeed.extensions

import com.example.jsonfeed.datamodel.Feed
import com.example.jsonfeed.datamodel.FeedItem
import com.example.jsonfeed.localdb.LocalItem

fun Feed?.toLocalItems(): List<LocalItem> {
    val localItems = mutableListOf<LocalItem>()
    if (this == null) return localItems
    this.cards?.let { cards ->
        for (item in cards) {
            item?.let { currentItem ->
                currentItem.id?.let { id ->
                    val localItem = currentItem.toLocalItem(id)
                    localItems.add(localItem)
                }
            }
        }
    }
    return localItems
}

private fun FeedItem.toLocalItem(id: String): LocalItem {
    return LocalItem(
        id,
        this.name,
        this.imageUrl,
        this.imageUrlHiRes,
        this.supertype,
        this.subtype,
        this.evolvesFrom,
        this.artist,
        this.rarity,
        this.series,
        this.set,
        this.setCode
    )
}
