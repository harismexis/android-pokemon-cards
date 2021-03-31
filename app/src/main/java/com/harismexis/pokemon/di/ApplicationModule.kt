package com.harismexis.pokemon.di

import android.content.Context
import com.harismexis.pokemon.application.MainApplication
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule {

    @Provides
    fun provideAppContext(app: MainApplication): Context {
        return app.applicationContext
    }

}