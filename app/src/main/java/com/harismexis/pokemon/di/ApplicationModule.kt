package com.harismexis.pokemon.di

import android.content.Context
import com.harismexis.pokemon.localdb.LocalDao
import com.harismexis.pokemon.localdb.LocalDatabase
import com.harismexis.pokemon.application.MainApplication
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