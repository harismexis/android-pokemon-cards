package com.example.jsonfeed.extensions

import com.example.jsonfeed.mockprovider.provideMockFeedValid
import com.example.jsonfeed.utils.verifyUiModelAgainstFeedItem
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
            val uiModel = uiModels[index]
            verifyUiModelAgainstFeedItem(uiModel, card!!)
        }
    }

}