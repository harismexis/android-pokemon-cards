package com.example.jsonfeed.workshared.di

import android.app.Application

import com.example.jsonfeed.workshared.localdb.LocalDao
import com.example.jsonfeed.workshared.localdb.LocalDatabase
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule {

    @Provides
    fun provideAuthDao(app: Application): LocalDao {
        return LocalDatabase.getDatabase(app).localDao()
    }
}