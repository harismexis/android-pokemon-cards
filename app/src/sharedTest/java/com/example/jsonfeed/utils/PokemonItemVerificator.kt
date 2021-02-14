package com.example.jsonfeed.utils

import com.example.jsonfeed.domain.Item
import com.example.jsonfeed.framework.datasource.db.PokemonEntity
import com.example.jsonfeed.framework.datasource.network.PokemonCard
import com.example.jsonfeed.framework.datasource.network.PokemonFeed
import org.junit.Assert

class PokemonItemVerificator {

    // TODO: Check Again here
    fun verifyItemsAgainstRemoteFeed(
        items: List<Item>,
        feed: PokemonFeed
    ) {
        if (feed == null || feed.cards.isNullOrEmpty()) {
            verifyListSizeForNoData(items)
            return
        }
        val cards = feed.cards!!
        cards.forEachIndexed lit@{ _, card ->
            if (card == null) return@lit
            items.forEachIndexed { _, item ->
                card.id?.let {
                    if (it == item.id) {
                        verifyItemAgainstRemoteItem(item, card)
                        return@lit
                    }
                }
            }
        }
    }

    fun verifyEntitiesAgainstItems(
        entities: List<PokemonEntity>,
        items: List<Item>
    ) {
        verifyListsHaveSameSize(entities, items)
        items.forEachIndexed { index, item ->
            val entity = entities[index]
            verifyEntityAgainstItem(entity, item)
        }
    }

    fun verifyItemsAgainstEntities(
        items: List<Item>,
        entities: List<PokemonEntity>
    ) {
        verifyListsHaveSameSize(items, entities)
        entities.forEachIndexed { index, entity ->
            val item = items[index]
            verifyItemAgainstEntity(item, entity)
        }
    }

    private fun verifyItemAgainstRemoteItem(
        actual: Item,
        expected: PokemonCard
    ) {
        Assert.assertEquals(expected.id, actual.id)
        Assert.assertEquals(expected.name, actual.name)
        Assert.assertEquals(expected.imageUrl, actual.imageUrl)
        Assert.assertEquals(expected.imageUrlHiRes, actual.imageUrlHiRes)
        Assert.assertEquals(expected.supertype, actual.supertype)
        Assert.assertEquals(expected.subtype, actual.subtype)
        Assert.assertEquals(expected.artist, actual.artist)
        Assert.assertEquals(expected.rarity, actual.rarity)
        Assert.assertEquals(expected.series, actual.series)
        Assert.assertEquals(expected.set, actual.set)
        Assert.assertEquals(expected.setCode, actual.setCode)
    }

    private fun verifyItemAgainstEntity(
        actual: Item,
        expected: PokemonEntity
    ) {
        Assert.assertEquals(expected.id, actual.id)
        Assert.assertEquals(expected.name, actual.name)
        Assert.assertEquals(expected.imageUrl, actual.imageUrl)
        Assert.assertEquals(expected.imageUrlHiRes, actual.imageUrlHiRes)
        Assert.assertEquals(expected.supertype, actual.supertype)
        Assert.assertEquals(expected.subtype, actual.subtype)
        Assert.assertEquals(expected.artist, actual.artist)
        Assert.assertEquals(expected.rarity, actual.rarity)
        Assert.assertEquals(expected.series, actual.series)
        Assert.assertEquals(expected.set, actual.set)
        Assert.assertEquals(expected.setCode, actual.setCode)
    }

    private fun verifyEntityAgainstItem(
        actual: PokemonEntity,
        expected: Item
    ) {
        Assert.assertEquals(expected.id, actual.id)
        Assert.assertEquals(expected.name, actual.name)
        Assert.assertEquals(expected.imageUrl, actual.imageUrl)
        Assert.assertEquals(expected.imageUrlHiRes, actual.imageUrlHiRes)
        Assert.assertEquals(expected.supertype, actual.supertype)
        Assert.assertEquals(expected.subtype, actual.subtype)
        Assert.assertEquals(expected.artist, actual.artist)
        Assert.assertEquals(expected.rarity, actual.rarity)
        Assert.assertEquals(expected.series, actual.series)
        Assert.assertEquals(expected.set, actual.set)
        Assert.assertEquals(expected.setCode, actual.setCode)
    }

}

