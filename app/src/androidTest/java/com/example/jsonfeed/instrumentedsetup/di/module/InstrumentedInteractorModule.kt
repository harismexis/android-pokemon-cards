package com.example.jsonfeed.instrumentedsetup.di.module

import com.example.jsonfeed.framework.Interactors
import com.example.jsonfeed.interactors.*

import dagger.Module
import dagger.Provides

import io.mockk.mockk

@Module
class InstrumentedInteractorModule {

    @Provides
    fun provideInteractors(
        iRRGetLocalFeedItem: IRRGetLocalItem,
        iRRGetLocalFeedItems: IRRGetLocalItems,
        iRRGetRemoteFeed: IRRGetRemoteItems,
        iRRStoreItems: IRRStoreItems
    ): Interactors {
        return mockk(relaxed = true)
    }

}