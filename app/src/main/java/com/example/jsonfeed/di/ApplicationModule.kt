package com.example.jsonfeed.di

import android.content.Context
import com.example.jsonfeed.application.MainApplication
import com.example.jsonfeed.db.PokemonDatabase
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