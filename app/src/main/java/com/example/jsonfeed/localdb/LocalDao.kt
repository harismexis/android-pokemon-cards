package com.example.jsonfeed.localdb

import androidx.room.*

@Dao
interface LocalDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItems(items: List<LocalItem>)

    @Query("SELECT * FROM feed_table WHERE id = :itemId")
    suspend fun getLocalItemById(itemId: String): LocalItem?

    @Query("SELECT * FROM feed_table")
    suspend fun getAllItems(): List<LocalItem>?

    @Query("DELETE FROM feed_table")
    suspend fun deleteAll()

}
