package com.example.jsonfeed.framework.di

import android.content.Context

import com.example.jsonfeed.framework.application.MainApplication
import com.example.jsonfeed.framework.db.LocalDao
import com.example.jsonfeed.framework.db.LocalDatabase

import dagger.Module
import dagger.Provides

@Module
class ApplicationModule {

    @Provides
    fun provideLocalDao(app: MainApplication): LocalDao {
        return LocalDatabase.getDatabase(app.applicationContext).getLocalDao()
    }

    @Provides
    fun provideAppContext(app: MainApplication): Context {
        return app.applicationContext
    }

}