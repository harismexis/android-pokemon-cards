package com.example.jsonfeed.instrumentedsetup.di.module

import com.example.jsonfeed.framework.Interactors
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