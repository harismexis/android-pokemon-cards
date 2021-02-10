package com.example.jsonfeed.framework.db

import android.content.Context

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [LocalItemEntity::class], version = 1, exportSchema = false)
abstract class LocalDatabase : RoomDatabase() {

    companion object {
        @Volatile
        var INSTANCE: LocalDatabase? = null

        fun getDatabase(
            context: Context
        ): LocalDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    LocalDatabase::class.java,
                    "feed_table"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }

    abstract fun getLocalDao(): LocalDao

}
