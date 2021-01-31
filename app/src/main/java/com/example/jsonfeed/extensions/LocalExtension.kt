package com.example.jsonfeed.extensions

import com.example.jsonfeed.localdb.LocalItem
import com.example.jsonfeed.uimodel.UiModel

fun List<UiModel>?.toLocalItems(): List<LocalItem> {
    val locals = mutableListOf<LocalItem>()
    if (this == null) return locals
    for (item in this) {
        val localItem = item.toLocalItem()
        locals.add(localItem)
    }
    return locals
}

fun UiModel.toLocalItem(): LocalItem {
    return LocalItem(
        this.id,
        this.name,
        this.imageUrl,
        this.imageUrlHiRes,
        this.supertype,
        this.subtype,
        this.evolvesFrom
    )
}
