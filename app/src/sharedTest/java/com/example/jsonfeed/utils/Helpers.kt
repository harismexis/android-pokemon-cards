package com.example.jsonfeed.utils

import com.example.jsonfeed.datamodel.FeedItem
import com.example.jsonfeed.localdb.LocalItem

import org.junit.Assert

fun verifyLocalItemAgainstFeedItem(
    expected: FeedItem,
    actual: LocalItem
) {
    Assert.assertEquals(expected.id, actual.id)
    Assert.assertEquals(expected.name, actual.name)
    Assert.assertEquals(expected.imageUrl, actual.imageUrl)
    Assert.assertEquals(expected.imageUrlHiRes, actual.imageUrlHiRes)
    Assert.assertEquals(expected.supertype, actual.supertype)
    Assert.assertEquals(expected.subtype, actual.subtype)
    Assert.assertEquals(expected.evolvesFrom, actual.evolvesFrom)
    Assert.assertEquals(expected.artist, actual.artist)
    Assert.assertEquals(expected.rarity, actual.rarity)
    Assert.assertEquals(expected.series, actual.series)
    Assert.assertEquals(expected.set, actual.set)
    Assert.assertEquals(expected.setCode, actual.setCode)
}