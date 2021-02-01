package com.example.jsonfeed.extensions

import com.example.jsonfeed.mockprovider.provideMockFeedValid
import com.example.jsonfeed.utils.verifyLocalItemAgainstFeedItem

import org.junit.Assert
import org.junit.Test

class FeedExtensionTest {

    @Test
    fun feedContainsValidItems_conversionToUiModelsIsCorrect() {
        // given
        val feed = provideMockFeedValid()

        // when
        val localItems = feed.toLocalItems()

        // then
        Assert.assertEquals(feed.cards!!.size, localItems.size)
        feed.cards!!.forEachIndexed { index, card ->
            val localItem = localItems[index]
            verifyLocalItemAgainstFeedItem(card!!, localItem)
        }
    }

}