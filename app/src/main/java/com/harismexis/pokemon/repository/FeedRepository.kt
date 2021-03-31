package com.harismexis.pokemon.repository

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.harismexis.pokemon.BuildConfig
import com.harismexis.pokemon.api.FeedApi
import com.harismexis.pokemon.datamodel.Feed
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FeedRepository @Inject constructor() {

    private val api: FeedApi

    init {
        api = createApi()
    }

    private fun createApi(): FeedApi {
        return buildRetrofit().create(FeedApi::class.java)
    }

    private fun buildRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.FEED_BASE_URL)
            .client(createOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create(buildGSON()))
            .build()
    }

    private fun createOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().build()
    }

    private fun buildGSON(): Gson {
        return GsonBuilder().setLenient().create()
    }

    suspend fun getJsonFeed(): Feed? {
        return api.getPokemonCards()
    }
}