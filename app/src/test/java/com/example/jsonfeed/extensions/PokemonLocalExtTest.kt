package com.example.jsonfeed.extensions

import com.example.jsonfeed.framework.extensions.toItems
import com.example.jsonfeed.framework.extensions.toPokemonEntities
import com.example.jsonfeed.shared.UnitTestSetup
import com.example.jsonfeed.utils.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class PokemonLocalExtTest : UnitTestSetup() {

    private val verificator = PokemonItemVerificator()

    @Test
    fun itemsAreConvertedToEntities_then_entityListIsAsExpected() {
        // given
        val items = mockParser.getMockItemsFromFeedWithAllItemsValid()

        // when
        val entities = items.toPokemonEntities()

        // then
        verifyListsHaveSameSize(items, entities)
        verifyListSizeWhenAllIdsValid(items)
        verifyListSizeWhenAllIdsValid(entities)
        verificator.verifyEntitiesAgainstItems(entities, items)
    }

    @Test
    fun entitiesAreConvertedToItems_then_itemListIsAsExpected() {
        // given
        val entities = mockParser.getMockPokemonEntitiesFromFeedWithAllItemsValid()

        // when
        val items = entities.toItems()

        // then
        verifyListsHaveSameSize(items, entities)
        verifyListSizeWhenAllIdsValid(entities)
        verifyListSizeWhenAllIdsValid(items)
        verificator.verifyItemsAgainstEntities(items, entities)
    }

    override fun initialiseClassUnderTest() {
        // do nothing
    }

}
