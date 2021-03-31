package com.harismexis.pokemon.instrumentedsetup.di.module

import android.content.Context
import com.harismexis.pokemon.framework.datasource.db.PokemonDatabase
import com.harismexis.pokemon.framework.datasource.db.PokemonLocalDao
import com.harismexis.pokemon.instrumentedsetup.application.InstrumentedMainApplication
import dagger.Module
import dagger.Provides

@Module
class InstrumentedApplicationModule {

    @Provides
    fun providesContext(app: InstrumentedMainApplication): Context {
        return app.applicationContext
    }

    @Provides
    fun provideLocalDao(app: InstrumentedMainApplication): PokemonLocalDao {
        return PokemonDatabase.getDatabase(app.applicationContext).getLocalDao()
    }


}