package com.example.jsonfeed.testutils

import com.example.jsonfeed.datamodel.Feed

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject

private fun convertToCreditResponse(jsonString: String?): Feed {
    val gson = GsonBuilder().setLenient().create()
    val json: JsonObject = gson.fromJson(jsonString, JsonObject::class.java)
    return Gson().fromJson(json, Feed::class.java)
}

fun provideMockFeed(): Feed {
    return convertToCreditResponse(getMockFeedNoFilter())
}