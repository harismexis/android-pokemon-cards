package com.example.jsonfeed.framework.remote

import com.example.jsonfeed.data.RemoteFeedDataSource
import com.example.jsonfeed.domain.RemoteFeed
import com.example.jsonfeed.domain.RemoteFeedItem

import javax.inject.Inject

class RetrofitRemoteFeedDataSource @Inject constructor(
    val remoteDao: RemoteFeedDao
) : RemoteFeedDataSource {

    override suspend fun getFeedData(): RemoteFeed? {
        val feed = remoteDao.getJsonFeed() ?: return null
        val cards = feed.cards ?: return null
        val remoteFeedItems = mutableListOf<RemoteFeedItem>()
        for (card in cards) {
            card?.let {
                remoteFeedItems.add(
                    RemoteFeedItem(
                        it.id,
                        it.name,
                        it.imageUrl,
                        it.imageUrlHiRes,
                        it.supertype,
                        it.subtype,
                        it.artist,
                        it.rarity,
                        it.series,
                        it.set,
                        it.setCode
                    )
                )
            }
        }
        return RemoteFeed(remoteFeedItems)
    }

}