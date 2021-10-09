package com.example.androidtraining.data.remote

import com.example.androidtraining.data.models.ImageData
import com.example.androidtraining.data.models.MyComments
import com.example.androidtraining.data.models.MyPostsData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitInterface {

    @GET("photos")
    fun getImages(): Call<List<ImageData>>
}

interface RetrofitPostInterface {

    @GET("posts")
    fun getPostsItem(): Call<List<MyPostsData>>
}

interface RetrofitPostCommentsInterface {

    @GET("comments?postId-")
    fun getComments(
        @Query("postId") postId: Long): Call<List<MyComments>>
}


