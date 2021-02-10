package com.example.jsonfeed.parser

import com.example.jsonfeed.framework.datasource.network.PokemonFeed
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject

abstract class BaseMockParser {

    companion object {
        const val TEST_FILE_FIVE_VALID_ITEMS = "test_data_five_valid_items.json"
        const val TEST_FILE_FIVE_ITEMS_BUT_TWO_IDS_ABSENT = "test_data_five_items_but_two_ids_absent.json"
        const val TEST_FILE_FIVE_ITEMS_BUT_TWO_EMPTY = "test_data_five_items_but_two_items_empty.json"
        const val TEST_FILE_FIVE_ITEMS_BUT_ONE_VALID = "test_data_five_items_but_one_valid.json"

        const val TEST_FILE_FIVE_ITEMS_ALL_IDS_ABSENT = "test_data_five_items_all_ids_absent.json"
        const val TEST_FILE_EMPTY_JSON = "test_data_empty_json.json"
        const val TEST_FILE_EMPTY_DATA_ARRAY = "test_data_empty_data_array.json"

        const val EXPECTED_NUM_MODELS_ALL_IDS_VALID = 5
        const val EXPECTED_NUM_MODELS_WHEN_TWO_IDS_ABSENT = 3
        const val EXPECTED_NUM_MODELS_WHEN_TWO_EMPTY = 3
        const val EXPECTED_NUM_MODELS_WHEN_ONE_ITEM_VALID = 1
        const val EXPECTED_NUM_MODELS_FOR_NO_DATA = 0
    }

    abstract fun getFileAsString(filePath: String): String

    fun getMockFeedAllIdsValid(): PokemonFeed = getMockFeed(mockDataAllIdsValid())
    fun getMockFeedSomeIdsAbsent(): PokemonFeed = getMockFeed(mockDataSomeIdsAbsent())
    fun getMockFeedSomeItemsEmpty(): PokemonFeed = getMockFeed(mockDataSomeItemsEmpty())
    fun getMockFeedOnlyOneItemValid(): PokemonFeed = getMockFeed(mockDataOnlyOneValid())
    fun getMockFeedAllIdsAbsent(): PokemonFeed = getMockFeed(mockDataAllIdsAbsent())
    fun getMockFeedEmptyJson(): PokemonFeed = getMockFeed(mockDataEmptyJson())
    fun getMockFeedEmptyDataArray(): PokemonFeed = getMockFeed(mockDataEmptyDataArray())

    private fun mockDataAllIdsValid(): String = getFileAsString(TEST_FILE_FIVE_VALID_ITEMS)
    private fun mockDataSomeIdsAbsent(): String = getFileAsString(TEST_FILE_FIVE_ITEMS_BUT_TWO_IDS_ABSENT)
    private fun mockDataSomeItemsEmpty(): String = getFileAsString(TEST_FILE_FIVE_ITEMS_BUT_TWO_EMPTY)
    private fun mockDataOnlyOneValid(): String = getFileAsString(TEST_FILE_FIVE_ITEMS_BUT_ONE_VALID)
    private fun mockDataAllIdsAbsent(): String = getFileAsString(TEST_FILE_FIVE_ITEMS_ALL_IDS_ABSENT)
    private fun mockDataEmptyJson(): String = getFileAsString(TEST_FILE_EMPTY_JSON)
    private fun mockDataEmptyDataArray(): String = getFileAsString(TEST_FILE_EMPTY_DATA_ARRAY)

    private fun convertToFeed(jsonString: String?): PokemonFeed {
        val gson = GsonBuilder().setLenient().create()
        val json: JsonObject = gson.fromJson(jsonString, JsonObject::class.java)
        return Gson().fromJson(json, PokemonFeed::class.java)
    }

    private fun getMockFeed(text: String): PokemonFeed {
        return convertToFeed(text)
    }

}