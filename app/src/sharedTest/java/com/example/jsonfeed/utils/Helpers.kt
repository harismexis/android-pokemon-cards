package com.example.jsonfeed.utils

import com.example.jsonfeed.datamodel.FeedItem
import com.example.jsonfeed.uimodel.UiModel

import org.junit.Assert

fun verifyUiModelAgainstFeedItem(
    model: UiModel,
    card: FeedItem
) {
    Assert.assertEquals(model.id, card.id)
    Assert.assertEquals(model.name, card.name)
    Assert.assertEquals(model.imageUrl, card.imageUrl)
    Assert.assertEquals(model.imageUrlHiRes, card.imageUrlHiRes)
    Assert.assertEquals(model.supertype, card.supertype)
    Assert.assertEquals(model.subtype, card.subtype)
    Assert.assertEquals(model.evolvesFrom, card.evolvesFrom)
    Assert.assertEquals(model.artist, card.artist)
    Assert.assertEquals(model.rarity, card.rarity)
    Assert.assertEquals(model.series, card.series)
    Assert.assertEquals(model.set, card.set)
    Assert.assertEquals(model.setCode, card.setCode)

}