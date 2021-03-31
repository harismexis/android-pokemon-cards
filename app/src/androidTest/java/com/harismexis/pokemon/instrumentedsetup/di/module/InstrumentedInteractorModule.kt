package com.harismexis.pokemon.instrumentedsetup.di.module

import com.harismexis.pokemon.framework.Interactors
import dagger.Module
import dagger.Provides
import io.mockk.mockk

@Module
class InstrumentedInteractorModule {

    @Provides
    fun provideInteractors(): Interactors {
        return mockk(relaxed = true)
    }

}