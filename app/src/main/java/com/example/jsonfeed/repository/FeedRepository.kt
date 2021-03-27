package com.example.jsonfeed.repository

// @Singleton
class FeedRepository {

//    private val api: FeedApi
//
//    init {
//        api = createApi()
//    }
//
//    private fun createApi(): FeedApi {
//        return buildRetrofit().create(FeedApi::class.java)
//    }
//
//    private fun buildRetrofit(): Retrofit {
//        return Retrofit.Builder()
//            .baseUrl(BuildConfig.FEED_BASE_URL)
//            .client(createOkHttpClient())
//            .addConverterFactory(GsonConverterFactory.create(buildGSON()))
//            .build()
//    }
//
//    private fun createOkHttpClient(): OkHttpClient {
//        return OkHttpClient.Builder().build()
//    }
//
//    private fun buildGSON(): Gson {
//        return GsonBuilder().setLenient().create()
//    }
//
//    suspend fun getJsonFeed(): Feed? {
//        return api.getPokemonCards()
//    }
}