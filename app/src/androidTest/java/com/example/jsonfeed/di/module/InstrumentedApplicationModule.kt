package com.example.jsonfeed.di.module

import android.content.Context

import com.example.jsonfeed.application.InstrumentedMainApplication
import com.example.jsonfeed.framework.datasource.db.RoomDao
import com.example.jsonfeed.framework.datasource.db.LocalDatabase

import dagger.Module
import dagger.Provides

@Module
class InstrumentedApplicationModule {

    @Provides
    fun providesContext(app: InstrumentedMainApplication): Context {
        return app.applicationContext
    }

    @Provides
    fun provideLocalDao(app: InstrumentedMainApplication): RoomDao {
        return LocalDatabase.getDatabase(app.applicationContext).getLocalDao()
    }


}