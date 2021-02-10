package com.example.jsonfeed.framework.db

import android.content.Context

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [LocalItemEntity::class], version = 1, exportSchema = false)
abstract class CleanLocalDatabase : RoomDatabase() {

    companion object {
        @Volatile
        var INSTANCE: CleanLocalDatabase? = null

        fun getDatabase(
            context: Context
        ): CleanLocalDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CleanLocalDatabase::class.java,
                    "clean_feed_table"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }

    abstract fun localDao(): CleanLocalDao

}
