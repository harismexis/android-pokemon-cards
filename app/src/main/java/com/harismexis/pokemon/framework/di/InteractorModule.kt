package com.harismexis.pokemon.framework.di

import com.harismexis.pokemon.framework.Interactors
import com.harismexis.pokemon.data.LocalRepository
import com.harismexis.pokemon.data.RemoteRepository
import com.harismexis.pokemon.framework.datasource.db.PokemonLocalDataSource
import com.harismexis.pokemon.framework.datasource.network.PokemonRemoteDataSource
import com.harismexis.pokemon.interactors.IRRGetLocalItem
import com.harismexis.pokemon.interactors.IRRGetLocalItems
import com.harismexis.pokemon.interactors.IRRGetRemoteItems
import com.harismexis.pokemon.interactors.IRRStoreItems
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