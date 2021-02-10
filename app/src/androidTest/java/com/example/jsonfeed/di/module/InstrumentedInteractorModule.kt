package com.example.jsonfeed.di.module

import com.example.jsonfeed.framework.Interactors
import com.example.jsonfeed.interactors.GetLocalItem
import com.example.jsonfeed.interactors.GetLocalItems
import com.example.jsonfeed.interactors.GetRemoteFeed
import com.example.jsonfeed.interactors.InsertLocalItems

import dagger.Module
import dagger.Provides

import io.mockk.mockk

@Module
class InstrumentedInteractorModule {

    @Provides
    fun provideInteractors(
        getLocalFeedItem: GetLocalItem,
        getLocalFeedItems: GetLocalItems,
        getRemoteFeed: GetRemoteFeed,
        insertLocalFeedItems: InsertLocalItems
    ): Interactors {
//        return Interactors(
//            getLocalFeedItem,
//            getLocalFeedItems,
//            getRemoteFeed,
//            insertLocalFeedItems
//        )
        return mockk(relaxed = true)
    }

//    @Provides
//    fun provideInteractorGetLocalFeedItem(
//        dataSource: RoomLocalFeedDataSource
//    ): GetLocalFeedItem {
//        return GetLocalFeedItem(LocalFeedRepository(dataSource))
//    }
//
//    @Provides
//    fun provideInteractorGetLocalFeedItems(
//        dataSource: RoomLocalFeedDataSource
//    ): GetLocalFeedItems {
//        return GetLocalFeedItems(LocalFeedRepository(dataSource))
//    }
//
//    @Provides
//    fun provideInteractorInsertLocalFeedItems(
//        dataSource: RoomLocalFeedDataSource
//    ): InsertLocalFeedItems {
//        return InsertLocalFeedItems(LocalFeedRepository(dataSource))
//    }
//
//    @Provides
//    fun provideInteractorGetRemoteFeed(
//        dataSource: RetrofitRemoteFeedDataSource
//    ): GetRemoteFeed {
//        return GetRemoteFeed(RemoteFeedRepository(dataSource))
//    }

}