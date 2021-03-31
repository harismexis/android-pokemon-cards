package com.harismexis.pokemon.localdb

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface LocalDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItems(items: List<LocalItem>)

    @Query("SELECT * FROM feed_table WHERE id = :itemId")
    suspend fun getItemById(itemId: String): LocalItem?

    @Query("SELECT * FROM feed_table")
    suspend fun getAllItems(): List<LocalItem>?

    @Query("DELETE FROM feed_table")
    suspend fun deleteAll()

}
