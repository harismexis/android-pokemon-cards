package com.example.jsonfeed.framework.db

import androidx.room.*

@Dao
interface CleanLocalDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItems(items: List<LocalItemEntity>)

    @Query("SELECT * FROM clean_feed_table WHERE id = :itemId")
    suspend fun getItemById(itemId: String): LocalItemEntity?

    @Query("SELECT * FROM clean_feed_table")
    suspend fun getAllItems(): List<LocalItemEntity>?

    @Query("DELETE FROM clean_feed_table")
    suspend fun deleteAll()

}
