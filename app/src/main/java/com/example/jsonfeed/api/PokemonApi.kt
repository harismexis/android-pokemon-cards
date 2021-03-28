package com.example.jsonfeed.api

import com.example.jsonfeed.model.PokemonFeed
import retrofit2.http.GET
import retrofit2.http.Query

interface PokemonApi {

    @GET("cards")
    suspend fun getPokemonCards(
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int,
    ): PokemonFeed

}