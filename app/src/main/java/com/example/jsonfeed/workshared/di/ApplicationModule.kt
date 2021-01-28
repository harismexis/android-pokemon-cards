package com.example.jsonfeed.workshared.di

import com.example.jsonfeed.workshared.application.MainApplication
import com.example.jsonfeed.workshared.localdb.LocalDao
import com.example.jsonfeed.workshared.localdb.LocalDatabase

import dagger.Module
import dagger.Provides

@Module
class ApplicationModule {

    @Provides
    fun provideLocalDao(app: MainApplication): LocalDao {
        return LocalDatabase.getDatabase(app.applicationContext).localDao()
    }

}