package com.example.androidtraining.data.remote

import com.example.androidtraining.constants.Constants
import com.example.androidtraining.data.models.ImageData
import com.example.androidtraining.data.models.MyComments
import com.example.androidtraining.data.models.MyPostsData
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitResponse {

    @GET("photos")
    fun getImages(): Call<List<ImageData>>

    @GET("posts")
    fun getPostsItem(): Call<List<MyPostsData>>

    @GET("comments?postId-")
    fun getComments(
        @Query("postId") postId: Long): Call<List<MyComments>>

    companion object {
        private var retrofitService: RetrofitResponse?=null
        private val moshi: Moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()

        fun getClient(): RetrofitResponse {
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(Constants.Common.baseURL)
                    .addConverterFactory(MoshiConverterFactory.create(moshi))
                    .build()
                retrofitService = retrofit.create(RetrofitResponse::class.java)

            }
            return retrofitService!!
        }
    }
}
