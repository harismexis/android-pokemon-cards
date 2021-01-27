package com.example.jsonfeed.workshared.localdb

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "feed_item_table")
data class LocalFeedItem(@PrimaryKey @ColumnInfo(name = "feed_item_name") val feedItemName: String)
