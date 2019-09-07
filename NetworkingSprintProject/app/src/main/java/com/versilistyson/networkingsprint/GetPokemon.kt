package com.versilistyson.networkingsprint


import com.versilistyson.networkingsprint.model.Pokemon
import com.versilistyson.networkingsprint.model.PokemonList
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

interface GetPokemon {

    @GET("pokemon/")
    fun getPokemon(): Call<Pokemon>

    @GET("pokemon/{name}")
    fun getPokemonById(@Path("id") id: Int): Call<Pokemon>

    companion object Factory {

            private const val BASE_URL = "https://pokeapi.co/api/v2/"

            fun create(): GetPokemon {
                val logger = HttpLoggingInterceptor()
                logger.level = HttpLoggingInterceptor.Level.BASIC
                logger.level = HttpLoggingInterceptor.Level.BODY

                val okHttpClient = OkHttpClient.Builder()
                    .addInterceptor(logger)
                    .retryOnConnectionFailure(false)
                    .readTimeout(10, TimeUnit.SECONDS)
                    .connectTimeout(15, TimeUnit.SECONDS)
                    .build()
                val retrofit = Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                return retrofit.create(GetPokemon::class.java)
        }
    }
}

