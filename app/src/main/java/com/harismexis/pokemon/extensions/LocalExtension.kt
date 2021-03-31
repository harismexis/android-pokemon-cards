package com.harismexis.pokemon.extensions

import com.harismexis.pokemon.localdb.LocalItem
import com.harismexis.pokemon.uimodel.UiModel

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
        this.artist,
        this.rarity,
        this.series,
        this.set,
        this.setCode
    )
}
