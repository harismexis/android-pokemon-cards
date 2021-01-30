package com.example.jsonfeed.extensions

import com.example.jsonfeed.localdb.LocalItem
import com.example.jsonfeed.uimodel.UiModel

fun List<UiModel>?.convertToLocalItems(): List<LocalItem> {
    val locals = mutableListOf<LocalItem>()
    if (this == null) return locals
    for (item in this) {
        val localItem = item.convertToLocalItem()
        locals.add(localItem)
    }
    return locals
}

fun UiModel.convertToLocalItem(): LocalItem {
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
