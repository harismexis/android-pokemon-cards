package com.example.jsonfeed.extensions

import com.example.jsonfeed.localdb.LocalItem
import com.example.jsonfeed.uimodel.UiModel

fun List<LocalItem>?.toUiModels(): List<UiModel> {
    val models = mutableListOf<UiModel>()
    if (this == null) return models
    for (item in this) {
        val model = item.toUiModel()
        models.add(model)
    }
    return models
}

fun LocalItem.toUiModel(): UiModel {
    return UiModel(
        this.id,
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
