package com.example.jsonfeed.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.jsonfeed.model.PokemonItem

@Database(
    entities = [PokemonItem::class, RemoteKeys::class],
    version = 1,
    exportSchema = false
)
abstract class PokemonDatabase : RoomDatabase() {

    companion object {
        @Volatile
        var INSTANCE: PokemonDatabase? = null
        private const val DATABASE_FILE_NAME = "pokemon_database"

        fun getDatabase(
            context: Context
        ): PokemonDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PokemonDatabase::class.java,
                    DATABASE_FILE_NAME
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }

    abstract fun getPokemonDao(): PokemonDao
    abstract fun getRemoteKeysDao(): RemoteKeysDao

}
