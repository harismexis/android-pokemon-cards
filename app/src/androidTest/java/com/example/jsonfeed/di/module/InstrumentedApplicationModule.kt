package com.example.jsonfeed.di.module

import android.content.Context

import com.example.jsonfeed.application.InstrumentedMainApplication
import com.example.jsonfeed.framework.db.LocalDao
import com.example.jsonfeed.framework.db.LocalDatabase

import dagger.Module
import dagger.Provides

@Module
class InstrumentedApplicationModule {

    @Provides
    fun providesContext(app: InstrumentedMainApplication): Context {
        return app.applicationContext
    }

    @Provides
    fun provideLocalDao(app: InstrumentedMainApplication): LocalDao {
        return LocalDatabase.getDatabase(app.applicationContext).getLocalDao()
    }


}