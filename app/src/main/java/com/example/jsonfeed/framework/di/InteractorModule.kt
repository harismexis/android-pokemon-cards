package com.example.jsonfeed.framework.di

import com.example.jsonfeed.data.LocalFeedRepository
import com.example.jsonfeed.data.RemoteFeedRepository

import com.example.jsonfeed.framework.Interactors
import com.example.jsonfeed.framework.db.RoomLocalFeedDataSource
import com.example.jsonfeed.framework.remote.RetrofitRemoteFeedDataSource

import com.example.jsonfeed.interactors.GetLocalFeedItem
import com.example.jsonfeed.interactors.GetLocalFeedItems
import com.example.jsonfeed.interactors.GetRemoteFeed
import com.example.jsonfeed.interactors.InsertLocalFeedItems

import dagger.Module
import dagger.Provides

@Module
class InteractorModule {

    @Provides
    fun provideInteractors(
        getLocalFeedItem: GetLocalFeedItem,
        getLocalFeedItems: GetLocalFeedItems,
        getRemoteFeed: GetRemoteFeed,
        insertLocalFeedItems: InsertLocalFeedItems
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
        dataSource: RoomLocalFeedDataSource
    ): GetLocalFeedItem {
        return GetLocalFeedItem(LocalFeedRepository(dataSource))
    }

    @Provides
    fun provideInteractorGetLocalFeedItems(
        dataSource: RoomLocalFeedDataSource
    ): GetLocalFeedItems {
        return GetLocalFeedItems(LocalFeedRepository(dataSource))
    }

    @Provides
    fun provideInteractorInsertLocalFeedItems(
        dataSource: RoomLocalFeedDataSource
    ): InsertLocalFeedItems {
        return InsertLocalFeedItems(LocalFeedRepository(dataSource))
    }

    @Provides
    fun provideInteractorGetRemoteFeed(
        dataSource: RetrofitRemoteFeedDataSource
    ): GetRemoteFeed {
        return GetRemoteFeed(RemoteFeedRepository(dataSource))
    }

}