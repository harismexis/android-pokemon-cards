package com.example.jsonfeed.extensions

import com.example.jsonfeed.datamodel.Feed
import com.example.jsonfeed.datamodel.FeedItem
import com.example.jsonfeed.uimodel.UiModel

fun Feed?.toUiModels(): List<UiModel> {
    val uiModels = mutableListOf<UiModel>()
    if (this == null) return uiModels
    this.cards?.let { cards ->
        for (item in cards) {
            item?.let { currentItem ->
                currentItem.id?.let { id ->
                    val uiModel = currentItem.toUiModel(id)
                    uiModels.add(uiModel)
                }
            }
        }
    }
    return uiModels
}

private fun FeedItem.toUiModel(id: String): UiModel {
    return UiModel(
        id,
        this.name,
        this.imageUrl,
        this.imageUrlHiRes,
        this.supertype,
        this.subtype,
        this.evolvesFrom
    )
}
