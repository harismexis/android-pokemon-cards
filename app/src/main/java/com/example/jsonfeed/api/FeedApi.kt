package com.example.jsonfeed.api

import com.example.jsonfeed.datamodel.Feed
import retrofit2.http.GET
import retrofit2.http.Query

interface FeedApi {

    @GET("cards")
    suspend fun getPokemonCards(): Feed?

    @GET("cards")
    suspend fun getPokemonCards(
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int,
    ): Feed

}