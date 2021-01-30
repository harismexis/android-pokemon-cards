package com.example.jsonfeed.api

import com.example.jsonfeed.datamodel.Feed

import retrofit2.http.GET

interface FeedApi {

    @GET("cards")
    suspend fun getPokemonCards(): Feed?

}