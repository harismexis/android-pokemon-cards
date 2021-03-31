package com.harismexis.pokemon.parser

import com.harismexis.pokemon.framework.datasource.db.PokemonEntity
import com.harismexis.pokemon.framework.datasource.network.PokemonFeed
import com.harismexis.pokemon.framework.extensions.toItems
import com.harismexis.pokemon.framework.extensions.toPokemonEntities
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import com.harismexis.pokemon.domain.Item

abstract class BaseMockParser {

    companion object {

        const val EXPECTED_NUM_MODELS_ALL_IDS_VALID = 5
        const val EXPECTED_NUM_MODELS_WHEN_TWO_IDS_ABSENT = 3
        const val EXPECTED_NUM_MODELS_WHEN_TWO_EMPTY = 3
        const val EXPECTED_NUM_MODELS_WHEN_ONE_ITEM_VALID = 1
        const val EXPECTED_NUM_MODELS_FOR_NO_DATA = 0

        private const val TEST_FILE_FIVE_VALID_ITEMS = "test_data_five_valid_items.json"
        private const val TEST_FILE_FIVE_ITEMS_BUT_TWO_IDS_ABSENT = "test_data_five_items_but_two_ids_absent.json"
        private const val TEST_FILE_FIVE_ITEMS_BUT_TWO_EMPTY = "test_data_five_items_but_two_items_empty.json"
        private const val TEST_FILE_FIVE_ITEMS_BUT_ONE_VALID = "test_data_five_items_but_one_valid.json"
        private const val TEST_FILE_FIVE_ITEMS_ALL_IDS_ABSENT = "test_data_five_items_all_ids_absent.json"
        private const val TEST_FILE_EMPTY_JSON = "test_data_empty_json.json"
        private const val TEST_FILE_EMPTY_DATA_ARRAY = "test_data_empty_data_array.json"
    }

    abstract fun getFileAsString(filePath: String): String

    fun getMockItemValid(): Item = getMockFeedAllIdsValid().toItems()[0]

    fun getMockPokemonEntitiesFromFeedWithAllItemsValid(): List<PokemonEntity> = getMockFeedAllIdsValid()
        .toItems().toPokemonEntities()
    fun getMockPokemonEntitiesFromFeedWithSomeIdsAbsent(): List<PokemonEntity> = getMockFeedSomeIdsAbsent()
        .toItems().toPokemonEntities()
    fun getMockPokemonEntitiesFromFeedWithAllIdsAbsent(): List<PokemonEntity> = getMockFeedAllIdsAbsent()
        .toItems().toPokemonEntities()

    fun getMockItemsFromFeedWithAllItemsValid(): List<Item> = getMockFeedAllIdsValid().toItems()
    fun getMockItemsFromFeedWithSomeIdsAbsent(): List<Item> = getMockFeedSomeIdsAbsent().toItems()
    fun getMockItemsFromFeedWithSomeItemsEmpty(): List<Item> = getMockFeedSomeItemsEmpty().toItems()
    fun getMockItemsFromFeedWithOneItemValid(): List<Item> = getMockFeedOneItemValid().toItems()
    fun getMockItemsFromFeedWithAllIdsAbsent(): List<Item> = getMockFeedAllIdsAbsent().toItems()
    fun getMockItemsFromFeedWithEmptyJson(): List<Item> = getMockFeedEmptyJson().toItems()
    fun getMockItemsFromFeedWithEmptyDataArray(): List<Item> = getMockFeedEmptyDataArray().toItems()

    fun getMockFeedAllIdsValid(): PokemonFeed = getMockPokemonFeed(mockDataAllIdsValid())
    fun getMockFeedSomeIdsAbsent(): PokemonFeed = getMockPokemonFeed(mockDataSomeIdsAbsent())
    fun getMockFeedSomeItemsEmpty(): PokemonFeed = getMockPokemonFeed(mockDataSomeItemsEmpty())
    fun getMockFeedOneItemValid(): PokemonFeed = getMockPokemonFeed(mockDataOneValid())
    fun getMockFeedAllIdsAbsent(): PokemonFeed = getMockPokemonFeed(mockDataAllIdsAbsent())
    fun getMockFeedEmptyJson(): PokemonFeed = getMockPokemonFeed(mockDataEmptyJson())
    fun getMockFeedEmptyDataArray(): PokemonFeed = getMockPokemonFeed(mockDataEmptyDataArray())

    private fun mockDataAllIdsValid(): String = getFileAsString(TEST_FILE_FIVE_VALID_ITEMS)
    private fun mockDataSomeIdsAbsent(): String = getFileAsString(
        TEST_FILE_FIVE_ITEMS_BUT_TWO_IDS_ABSENT
    )
    private fun mockDataSomeItemsEmpty(): String = getFileAsString(
        TEST_FILE_FIVE_ITEMS_BUT_TWO_EMPTY
    )
    private fun mockDataOneValid(): String = getFileAsString(TEST_FILE_FIVE_ITEMS_BUT_ONE_VALID)
    private fun mockDataAllIdsAbsent(): String = getFileAsString(TEST_FILE_FIVE_ITEMS_ALL_IDS_ABSENT)
    private fun mockDataEmptyJson(): String = getFileAsString(TEST_FILE_EMPTY_JSON)
    private fun mockDataEmptyDataArray(): String = getFileAsString(TEST_FILE_EMPTY_DATA_ARRAY)

    private fun getMockPokemonFeed(text: String): PokemonFeed {
        return convertToFeed(text)
    }

    private inline fun <reified T> convertToFeed(jsonString: String?): T {
        val gson = GsonBuilder().setLenient().create()
        val json: JsonObject = gson.fromJson(jsonString, JsonObject::class.java)
        return Gson().fromJson(json, T::class.java)
    }

}