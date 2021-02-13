package com.example.jsonfeed.extensions

import com.example.jsonfeed.framework.extensions.toItems
import com.example.jsonfeed.shared.UnitTestSetup
import com.example.jsonfeed.utils.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class PokemonRemoteExtTest : UnitTestSetup() {

    @Test
    fun feedHasAllItemsValid_then_conversionToItemsIsCorrect() {
        // given
        val feed = mockParser.getMockFeedAllIdsValid()

        // when
        val items = feed.toItems()

        // then
        verifyListsHaveSameSize(feed.cards!!, items)
        verifyListSizeWhenAllIdsValid(feed.cards!!)
        verifyListSizeWhenAllIdsValid(items)

        feed.cards!!.forEachIndexed { index, feedItem ->
            val item = items[index]
            verifyItemAgainstPokemonCard(item, feedItem!!)
        }
    }

    @Test
    fun feedHasSomeIdsAbsent_then_conversionToItemsIsCorrect() {
        // given
        val feed = mockParser.getMockFeedSomeIdsAbsent()

        // when
        val items = feed.toItems()

        // then
        verifyListSizeWhenSomeIdsAbsent(items)
        verifyItemsAgainstRemoteFeed(feed, items)
    }

    @Test
    fun feedHasSomeEmptyItems_then_conversionToItemsIsCorrect() {
        // given
        val feed = mockParser.getMockFeedSomeItemsEmpty()

        // when
        val items = feed.toItems()

        // then
        verifyListSizeWhenSomeItemsEmpty(items)
        verifyItemsAgainstRemoteFeed(feed, items)
    }

    @Test
    fun feedHasAllIdsAbsent_then_itemListIsEmpty() {
        // given
        val feed = mockParser.getMockFeedAllIdsAbsent()

        // when
        val items = feed.toItems()

        // then
        verifyListSizeForNoData(items)
    }

    @Test
    fun feedIsAnEmptyJson_then_itemListIsEmpty() {
        // given
        val feed = mockParser.getMockFeedEmptyJson()

        // when
        val items = feed.toItems()

        // then
        verifyListSizeForNoData(items)
    }

    override fun initialiseClassUnderTest() {
        // Do nothing
    }

}
