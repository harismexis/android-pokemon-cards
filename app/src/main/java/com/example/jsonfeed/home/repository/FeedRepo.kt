package com.example.jsonfeed.home.repository

import com.example.jsonfeed.BuildConfig
import com.example.jsonfeed.home.api.FeedApi
import com.example.jsonfeed.model.Feed
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

open class FeedRepo {

    private val api: FeedApi

    init {
        api = createApi()
    }

    private fun createApi(): FeedApi {
        return buildRetrofit().create(FeedApi::class.java)
    }

    open fun buildRetrofit(): Retrofit {
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
        return api.getJsonFeed()
    }
}