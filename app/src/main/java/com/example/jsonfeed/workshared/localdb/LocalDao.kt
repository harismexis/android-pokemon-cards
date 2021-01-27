package com.example.jsonfeed.workshared.localdb

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface LocalDao {

    @Query("SELECT * from feed_item_table ORDER BY feed_item_name DESC LIMIT 1")
    suspend fun getFeedItems(): LocalFeedItem?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFeedItem(bearerToken: LocalFeedItem)

    @Query("DELETE FROM feed_item_table")
    suspend fun deleteAll()
}
