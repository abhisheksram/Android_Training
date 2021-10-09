package com.example.androidtraining.data.remote

import com.example.androidtraining.data.models.DogsBreed
import com.example.androidtraining.data.models.DogsImages
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface DogsResponse {


    @GET("api/breeds/list/all")
    suspend fun getDogBreed(): Response<DogsBreed>

    @GET("api/breed/{breedName}/images/random")
    suspend fun getDogsImages(@Query("{breedName}") breedName: String): Response<DogsImages>


    companion object {
        private var dogsResponse: DogsResponse? = null
        private val moshi: Moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
        private const val baseUrl = "https://dog.ceo/api/"

        fun getDogsBreed(): DogsResponse {
            if (dogsResponse == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(MoshiConverterFactory.create(moshi))
                    .build()
                dogsResponse = retrofit.create(DogsResponse::class.java)

            }
            return dogsResponse!!
        }
    }
}
