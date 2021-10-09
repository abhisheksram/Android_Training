package com.example.androidtraining.data.remote

import com.example.androidtraining.data.models.DogsData
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface DogsResponse {


    @GET("breed/{breedName}/images/random")
    fun getImageByUrlAsync(@Path("breedName") breedName: String): Call<DogsData<String>>

    @GET("breeds/list/all")
    fun getBreedsListAsync(): Call<DogsData<Map<String, List<String>>>>

    @GET("breed/{breedName}/images/random")
    suspend fun getImageByUrl(@Path("breedName") breedName: String): DogsData<String>

    @GET("breeds/list/all")
    suspend fun getBreedsList(): DogsData<Map<String, List<String>>>

    companion object {
        private var dogsResponse: DogsResponse? = null
        private val moshi: Moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
        private const val baseUrl = "https://dog.ceo/api/"

        fun apiDogs(): DogsResponse {
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
