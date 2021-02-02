package com.example.jsonfeed.extensions

import com.example.jsonfeed.base.BaseTestSetup
import com.example.jsonfeed.mockprovider.getMockFeedAllIdsValid
import com.example.jsonfeed.utils.verifyLocalItemAgainstFeedItem

import org.junit.Test

class FeedExtensionTest : BaseTestSetup() {

    @Test
    fun feedContainsValidItems_conversionToLocalItemsIsCorrect() {
        // given
        val feed = getMockFeedAllIdsValid()

        // when
        val localItems = feed.toLocalItems()

        // then
        verifyListsHaveSameSize(feed.cards!!, localItems)
        verifyListSizeWhenAllIdsValid(feed.cards!!)
        verifyListSizeWhenAllIdsValid(localItems)

        feed.cards!!.forEachIndexed { index, card ->
            val localItem = localItems[index]
            verifyLocalItemAgainstFeedItem(card!!, localItem)
        }
    }

}