package com.harismexis.pokemon.api

import com.harismexis.pokemon.datamodel.remote.Feed
import retrofit2.http.GET
import retrofit2.http.Query

interface PokemonApi {

    @GET("cards")
    suspend fun getPokemonCards(
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int,
    ): Feed

}