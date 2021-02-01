package com.example.jsonfeed.mockprovider

import com.example.jsonfeed.datamodel.Feed

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject

private fun convertToFeed(jsonString: String?): Feed {
    val gson = GsonBuilder().setLenient().create()
    val json: JsonObject = gson.fromJson(jsonString, JsonObject::class.java)
    return Gson().fromJson(json, Feed::class.java)
}

fun provideMockFeedValid(): Feed {
    return convertToFeed(getMockFeedValid())
}

fun provideMockFeedAllIdsMissing(): Feed {
    return convertToFeed(getMockFeedAllIdsMissing())
}

