package com.example.jsonfeed.utils

import com.example.jsonfeed.domain.Item
import com.example.jsonfeed.framework.datasource.db.PokemonEntity
import com.example.jsonfeed.framework.datasource.network.PokemonCard
import com.example.jsonfeed.framework.datasource.network.PokemonFeed
import org.junit.Assert

fun verifyItemAgainstPokemonCard(
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

fun verifyItemAgainstPokemonEntity(
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

fun verifyPokemonEntityAgainstItem(
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

fun verifyItemsAgainstRemoteFeed(
    feed: PokemonFeed,
    items: List<Item>
) {
    feed.cards!!.forEachIndexed lit@{ _, card ->
        if (card == null) return@lit
        items.forEachIndexed { _, item ->
            card.id?.let {
                if (it == item.id) {
                    verifyItemAgainstPokemonCard(item, card)
                    return@lit
                }
            }
        }
    }
}