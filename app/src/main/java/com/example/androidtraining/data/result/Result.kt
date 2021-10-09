package com.example.androidtraining.data.result

sealed class Result<out T>{
    data class Success<out R>(val value:R):Result<R>()
    data class Failure(
        val message:String?=null,
        val throwable: Throwable?=null
    ):Result<Nothing>()
}

class Results<out T>(val value: T?, val throwable: Throwable?)
