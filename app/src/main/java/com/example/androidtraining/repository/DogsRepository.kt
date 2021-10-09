package com.example.androidtraining.repository

import com.example.androidtraining.data.remote.DogsResponse
import com.example.androidtraining.data.models.DogsBreed
import com.example.androidtraining.data.models.DogsImages
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import retrofit2.Call

class DogsRepository {

    private val dogsResponse: DogsResponse? = null
    suspend fun getDogsImages(breedName: String): DogsImages? {
        val request = dogsResponse?.getDogsImages(breedName)

        if (request?.isSuccessful == true) {
            return request.body()!!
        }
        return null
    }

    suspend fun getDogsBreed(): DogsBreed? {
        val request = dogsResponse?.getDogBreed()

        if (request?.isSuccessful == true) {
            return request.body()!!
        }
        return null
    }

    suspend fun getFiveImages(): Unit = withContext(Dispatchers.IO) {

    }

}



