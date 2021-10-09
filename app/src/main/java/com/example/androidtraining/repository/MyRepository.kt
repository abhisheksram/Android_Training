package com.example.androidtraining.repository

import com.example.androidtraining.data.remote.RetrofitResponse
import com.example.androidtraining.data.models.ImageData
import com.example.androidtraining.data.models.MyComments
import com.example.androidtraining.data.models.MyPostsData
import retrofit2.Call


class MyRepository  constructor(private val retrofitResponse: RetrofitResponse):IMyRepository {

    override fun getImages():Call<List<ImageData>> = retrofitResponse.getImages()
    override fun getPostsItems(): Call<List<MyPostsData>> = retrofitResponse.getPostsItem()
    override fun getComments(postId: Long): Call<List<MyComments>> = retrofitResponse.getComments(postId)

}

interface IMyRepository {

    fun getImages(): Call<List<ImageData>>
    fun getPostsItems(): Call<List<MyPostsData>>
    fun getComments(postId: Long): Call<List<MyComments>>

}
