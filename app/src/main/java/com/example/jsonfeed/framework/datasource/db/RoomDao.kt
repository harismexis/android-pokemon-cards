package com.example.jsonfeed.framework.datasource.db

import androidx.room.*

@Dao
interface RoomDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItems(items: List<LocalItemEntity>)

    @Query("SELECT * FROM feed_table WHERE id = :itemId")
    suspend fun getItemById(itemId: String): LocalItemEntity?

    @Query("SELECT * FROM feed_table")
    suspend fun getAllItems(): List<LocalItemEntity>?

    @Query("DELETE FROM feed_table")
    suspend fun deleteAll()

}
