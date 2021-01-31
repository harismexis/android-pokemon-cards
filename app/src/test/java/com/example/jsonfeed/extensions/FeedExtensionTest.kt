package com.example.jsonfeed.extensions

import com.example.jsonfeed.datamodel.FeedItem
import com.example.jsonfeed.mockprovider.provideMockFeedValid
import com.example.jsonfeed.uimodel.UiModel
import org.junit.Assert
import org.junit.Test

class FeedExtensionTest {

    @Test
    fun feedContainsValidItems_conversionToUiModelsIsCorrect() {
        // given
        val feed = provideMockFeedValid()

        // when
        val uiModels = feed.toUiModels()

        // then
        Assert.assertEquals(feed.cards!!.size, uiModels.size)
        feed.cards!!.forEachIndexed { index, card ->
            val model = uiModels[index]
            verifyUiModelAgainstCard(model, card!!)
        }
    }

    private fun verifyUiModelAgainstCard(model: UiModel, card: FeedItem) {
        Assert.assertEquals(model.id, card.id)
        Assert.assertEquals(model.name, card.name)
        Assert.assertEquals(model.imageUrl, card.imageUrl)
        Assert.assertEquals(model.imageUrlHiRes, card.imageUrlHiRes)
        Assert.assertEquals(model.supertype, card.supertype)
        Assert.assertEquals(model.subtype, card.subtype)
        Assert.assertEquals(model.evolvesFrom, card.evolvesFrom)
    }

}