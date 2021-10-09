package com.example.androidtraining.data.models

import com.squareup.moshi.Json

data class MyPostsData(
    val title: String,
    val id: Long,
    val body: String
)

data class ImageData(
    val id: Long,
    val title: String,
    val url: String,
    @Json(name = "thumbnailUrl")
    val thumbnailURL: String
)

data class MyComments(
    @Json(name = "postId")
    val postID: Long,
    val id: Long,
    val name: String,
    val email: String,
    val body: String
)
