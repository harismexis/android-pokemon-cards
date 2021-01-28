package com.example.jsonfeed.workshared.localdb

import androidx.room.ColumnInfo

data class NameTuple(
    @ColumnInfo(name = "feed_item_name") val itemName: String?,
    @ColumnInfo(name = "feed_item_metadata") val itemMeta: String?
)