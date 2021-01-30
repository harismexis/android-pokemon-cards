package com.example.jsonfeed.localdb

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "feed_table")
data class LocalFeedItem(
    @PrimaryKey @ColumnInfo(name = "id") val id: String,
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "imageUrl") val imageUrl: String?,
    @ColumnInfo(name = "imageUrlHiRes") val imageUrlHiRes: String?,
    @ColumnInfo(name = "supertype") val supertype: String?,
    @ColumnInfo(name = "subtype") val subtype: String?,
    @ColumnInfo(name = "evolvesFrom") val evolvesFrom: String?
)
