package com.harismexis.pokemon.api

import com.harismexis.pokemon.datamodel.Feed

import retrofit2.http.GET

interface FeedApi {

    @GET("cards")
    suspend fun getPokemonCards(): Feed?

}