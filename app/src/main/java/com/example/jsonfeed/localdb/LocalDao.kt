package com.example.jsonfeed.localdb

import androidx.room.*

@Dao
interface LocalDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItems(items: List<LocalFeedItem>)

    @Query("SELECT * FROM feed_table WHERE id = :itemId")
    suspend fun getFeedItemById(itemId: Int): LocalFeedItem?

    @Query("SELECT * FROM feed_table")
    suspend fun getFeedItems(): List<LocalFeedItem>?

    @Query("DELETE FROM feed_table")
    suspend fun deleteAll()

}
