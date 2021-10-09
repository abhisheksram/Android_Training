package com.example.androidtraining.data.models

import com.google.gson.annotations.SerializedName

data class Dog(
    val breed : String?,
    val imageUrl: String?
)

data class DogsData<T>(
    @SerializedName("status") val status: String,
    @SerializedName("message") val message: T
)