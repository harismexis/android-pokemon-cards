package com.example.jsonfeed.home.api

import com.example.jsonfeed.model.Feed
import retrofit2.http.GET
import retrofit2.http.Query

interface FeedApi {

    @GET("apod")
    suspend fun getJsonFeed(
//        @Query("date") dateOfApod: String?,
//        @Query("hd") isHD: Boolean?,
//        @Query("api_key") apiKey: String
    ): Feed?

}