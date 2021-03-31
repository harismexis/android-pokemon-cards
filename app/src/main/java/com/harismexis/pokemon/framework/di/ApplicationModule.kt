package com.harismexis.pokemon.framework.di

import android.content.Context
import com.harismexis.pokemon.framework.application.MainApplication
import com.harismexis.pokemon.framework.datasource.db.PokemonDatabase
import com.harismexis.pokemon.framework.datasource.db.PokemonLocalDao
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule {

    @Provides
    fun provideLocalDao(app: MainApplication): PokemonLocalDao {
        return PokemonDatabase.getDatabase(app.applicationContext).getLocalDao()
    }

    @Provides
    fun provideAppContext(app: MainApplication): Context {
        return app.applicationContext
    }

}