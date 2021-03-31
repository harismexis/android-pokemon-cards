package com.harismexis.pokemon.db

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.harismexis.pokemon.model.PokemonItem

@Dao
interface PokemonDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItems(items: List<PokemonItem>)

    @Query("SELECT * FROM pokemon_table WHERE id = :itemId")
    suspend fun getItemById(itemId: String): PokemonItem?

    @Query("SELECT * FROM pokemon_table")
    fun getAllItems(): PagingSource<Int, PokemonItem>

    @Query("DELETE FROM pokemon_table")
    suspend fun deleteAll()

}
