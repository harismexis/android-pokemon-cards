package com.harismexis.pokemon.di

import android.content.Context
import com.harismexis.pokemon.application.MainApplication
import com.harismexis.pokemon.db.PokemonDatabase
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule {

    @Provides
    fun provideAppContext(app: MainApplication): Context {
        return app.applicationContext
    }

    @Provides
    fun providePokemonDatabase(context: Context): PokemonDatabase {
        return PokemonDatabase.getDatabase(context.applicationContext)
    }

}