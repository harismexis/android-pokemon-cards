package com.example.jsonfeed.extensions

import com.example.jsonfeed.base.BaseTestSetup
import com.example.jsonfeed.mockprovider.getMockFeedAllIdsAbsent
import com.example.jsonfeed.mockprovider.getMockFeedAllIdsValid
import com.example.jsonfeed.mockprovider.getMockFeedSomeIdsAbsent
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

        feed.cards!!.forEachIndexed { index, feedItem ->
            val localItem = localItems[index]
            verifyLocalItemAgainstFeedItem(feedItem!!, localItem)
        }
    }

    @Test
    fun feedHasSomeIdsAbsent_conversionToLocalItemsIsCorrect() {
        // given
        val feed = getMockFeedSomeIdsAbsent()

        // when
        val localItems = feed.toLocalItems()

        // then
        verifyListSizeWhenSomeIdsAbsent(localItems)
        feed.cards!!.forEachIndexed lit@{ _, feedItem ->
            localItems.forEachIndexed { _, localItem ->
                feedItem?.id?.let {
                    if (it == localItem.id) {
                        verifyLocalItemAgainstFeedItem(feedItem, localItem)
                        return@lit
                    }
                }
            }
        }
    }

    @Test
    fun feedHasAllIdsAbsent_localItemsAreEmpty() {
        // given
        val feed = getMockFeedAllIdsAbsent()

        // when
        val localItems = feed.toLocalItems()

        // then
        verifyListSizeWhenAllIdsAbsent(localItems)
    }


}