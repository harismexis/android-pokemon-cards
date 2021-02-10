package com.example.jsonfeed.framework.datasource.network

import com.example.jsonfeed.data.RemoteDataSource
import com.example.jsonfeed.domain.RemoteFeed
import com.example.jsonfeed.domain.RemoteItem

import javax.inject.Inject

class NetworkDataSource @Inject constructor(
    private val dao: RetrofitDao
) : RemoteDataSource {

    override suspend fun getFeedData(): RemoteFeed? {
        val feed = dao.getPokemonCards() ?: return null
        val cards = feed.cards ?: return null
        val remoteFeedItems = mutableListOf<RemoteItem>()
        for (card in cards) {
            card?.let {
                remoteFeedItems.add(
                    RemoteItem(
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