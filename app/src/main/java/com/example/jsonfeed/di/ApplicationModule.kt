package com.example.jsonfeed.di

import android.content.Context
import com.example.jsonfeed.application.MainApplication
import com.example.jsonfeed.localdb.LocalDao
import com.example.jsonfeed.localdb.LocalDatabase

import dagger.Module
import dagger.Provides

@Module
class ApplicationModule {

    @Provides
    fun provideLocalDao(app: MainApplication): LocalDao {
        return LocalDatabase.getDatabase(app.applicationContext).localDao()
    }

    @Provides
    fun provideAppContext(app: MainApplication): Context {
        return app.applicationContext
    }

}