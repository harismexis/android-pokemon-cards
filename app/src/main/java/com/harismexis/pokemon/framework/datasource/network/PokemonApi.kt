package com.harismexis.pokemon.framework.datasource.network

import retrofit2.http.GET

interface PokemonApi {

    @GET("cards")
    suspend fun getPokemonCards(): PokemonFeed?

}