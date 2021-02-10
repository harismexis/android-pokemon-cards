package com.example.jsonfeed.framework.datasource.network

import com.example.jsonfeed.framework.datamodel.Feed

import retrofit2.http.GET

interface FeedApi {

    @GET("cards")
    suspend fun getPokemonCards(): Feed?

}