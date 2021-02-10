package com.example.jsonfeed.mockprovider

import com.example.jsonfeed.framework.datasource.network.PokemonFeed

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject

private fun convertToFeed(jsonString: String?): PokemonFeed {
    val gson = GsonBuilder().setLenient().create()
    val json: JsonObject = gson.fromJson(jsonString, JsonObject::class.java)
    return Gson().fromJson(json, PokemonFeed::class.java)
}

fun getMockFeedAllIdsValid(): PokemonFeed {
    return convertToFeed(mockFeedAllIdsValid())
}

fun getMockFeedSomeIdsAbsent(): PokemonFeed {
    return convertToFeed(mockFeedSomeIdsAbsent())
}

fun getMockFeedAllIdsAbsent(): PokemonFeed {
    return convertToFeed(mockFeedAllIdsAbsent())
}

fun getMockFeedSomeItemsEmpty(): PokemonFeed {
    return convertToFeed(mockFeedSomeItemsEmpty())
}

fun getMockFeedEmptyJson(): PokemonFeed {
    return convertToFeed(mockFeedEmptyJson())
}

