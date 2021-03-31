package com.harismexis.pokemon.mockprovider

import com.harismexis.pokemon.datamodel.remote.Feed

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject

private fun convertToFeed(jsonString: String?): Feed {
    val gson = GsonBuilder().setLenient().create()
    val json: JsonObject = gson.fromJson(jsonString, JsonObject::class.java)
    return Gson().fromJson(json, Feed::class.java)
}

fun getMockFeedAllIdsValid(): Feed {
    return convertToFeed(mockFeedAllIdsValid())
}

fun getMockFeedSomeIdsAbsent(): Feed {
    return convertToFeed(mockFeedSomeIdsAbsent())
}

fun getMockFeedAllIdsAbsent(): Feed {
    return convertToFeed(mockFeedAllIdsAbsent())
}

fun getMockFeedSomeItemsEmpty(): Feed {
    return convertToFeed(mockFeedSomeItemsEmpty())
}

fun getMockFeedEmptyJson(): Feed {
    return convertToFeed(mockFeedEmptyJson())
}

