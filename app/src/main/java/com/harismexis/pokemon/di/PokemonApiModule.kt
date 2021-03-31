package com.harismexis.pokemon.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.harismexis.pokemon.BuildConfig
import com.harismexis.pokemon.api.PokemonApi
import com.harismexis.pokemon.application.MainApplication
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class PokemonApiModule {

    @Provides
    fun providePokemonApi(retrofit: Retrofit): PokemonApi {
        return retrofit.create(PokemonApi::class.java)
    }

    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gson: Gson
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.FEED_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Provides
    fun provideOkHttpClient(app: MainApplication): OkHttpClient {
        return OkHttpClient.Builder()
            .build()
    }

    @Provides
    fun provideGSON(): Gson {
        return GsonBuilder().setLenient().create()
    }

}