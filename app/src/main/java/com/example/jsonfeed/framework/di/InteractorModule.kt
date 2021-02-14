package com.example.jsonfeed.framework.di

import com.example.jsonfeed.data.LocalRepository
import com.example.jsonfeed.data.RemoteRepository

import com.example.jsonfeed.framework.Interactors
import com.example.jsonfeed.framework.datasource.db.PokemonLocalDataSource
import com.example.jsonfeed.framework.datasource.network.PokemonRemoteDataSource
import com.example.jsonfeed.interactors.*

import dagger.Module
import dagger.Provides

@Module
class InteractorModule {

    @Provides
    fun provideInteractors(
        iRRGetLocalFeedItem: IRRGetLocalItem,
        iRRGetLocalFeedItems: IRRGetLocalItems,
        iRRGetRemoteItems: IRRGetRemoteItems,
        iRRStoreItems: IRRStoreItems
    ): Interactors {
        return Interactors(
            iRRGetLocalFeedItem,
            iRRGetLocalFeedItems,
            iRRGetRemoteItems,
            iRRStoreItems
        )
    }

    @Provides
    fun provideIRRGetLocalFeedItem(
        dataSource: PokemonLocalDataSource
    ): IRRGetLocalItem {
        return IRRGetLocalItem(LocalRepository(dataSource))
    }

    @Provides
    fun provideIRRGetLocalFeedItems(
        dataSource: PokemonLocalDataSource
    ): IRRGetLocalItems {
        return IRRGetLocalItems(LocalRepository(dataSource))
    }

    @Provides
    fun provideIRRInsertLocalFeedItems(
        dataSource: PokemonLocalDataSource
    ): IRRStoreItems {
        return IRRStoreItems(LocalRepository(dataSource))
    }

    @Provides
    fun provideIRRGetRemoteItems(
        dataSource: PokemonRemoteDataSource
    ): IRRGetRemoteItems {
        return IRRGetRemoteItems(RemoteRepository(dataSource))
    }

}