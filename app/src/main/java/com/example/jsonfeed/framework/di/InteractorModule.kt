package com.example.jsonfeed.framework.di

import com.example.jsonfeed.data.LocalRepository
import com.example.jsonfeed.data.RemoteRepository

import com.example.jsonfeed.framework.Interactors
import com.example.jsonfeed.framework.datasource.db.LocalStorageDataSource
import com.example.jsonfeed.framework.datasource.network.NetworkDataSource

import com.example.jsonfeed.interactors.GetLocalItem
import com.example.jsonfeed.interactors.GetLocalItems
import com.example.jsonfeed.interactors.GetRemoteFeed
import com.example.jsonfeed.interactors.InsertLocalItems

import dagger.Module
import dagger.Provides

@Module
class InteractorModule {

    @Provides
    fun provideInteractors(
        getLocalFeedItem: GetLocalItem,
        getLocalFeedItems: GetLocalItems,
        getRemoteFeed: GetRemoteFeed,
        insertLocalFeedItems: InsertLocalItems
    ): Interactors {
        return Interactors(
            getLocalFeedItem,
            getLocalFeedItems,
            getRemoteFeed,
            insertLocalFeedItems
        )
    }

    @Provides
    fun provideInteractorGetLocalFeedItem(
        dataSource: LocalStorageDataSource
    ): GetLocalItem {
        return GetLocalItem(LocalRepository(dataSource))
    }

    @Provides
    fun provideInteractorGetLocalFeedItems(
        dataSource: LocalStorageDataSource
    ): GetLocalItems {
        return GetLocalItems(LocalRepository(dataSource))
    }

    @Provides
    fun provideInteractorInsertLocalFeedItems(
        dataSource: LocalStorageDataSource
    ): InsertLocalItems {
        return InsertLocalItems(LocalRepository(dataSource))
    }

    @Provides
    fun provideInteractorGetRemoteFeed(
        dataSource: NetworkDataSource
    ): GetRemoteFeed {
        return GetRemoteFeed(RemoteRepository(dataSource))
    }

}