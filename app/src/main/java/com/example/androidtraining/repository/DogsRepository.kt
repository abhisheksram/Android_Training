package com.example.androidtraining.repository

import com.example.androidtraining.data.models.*
import com.example.androidtraining.data.remote.DogsResponse
import com.example.androidtraining.data.result.GeneralResult
import com.example.androidtraining.data.result.Results
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import java.util.*


class DogsRepository (private val dogsResponse: DogsResponse) : IDogsRepository {

    override suspend fun getListOfDogs(): Results<List<Dog>> {

        val list = mutableListOf<Dog>()

        val dogBreedList = dogsResponse.getBreedsList().message.keys.toList()

        dogBreedList.forEach {
            val dogImage = dogsResponse.getImageByUrl(it).message
            list.add(Dog(it, dogImage))
        }
        return Results(list, null)
    }

    override suspend fun getSixDogs():  GeneralResult  = withContext(Dispatchers.IO) {

        val list = mutableListOf<Dog>()

        val dogBreedListDeferred = async { dogsResponse.getBreedsListAsync().execute() }

        val dogBreedListResponse = dogBreedListDeferred.await()

        if (dogBreedListResponse.isSuccessful) {

            val dogBreedOneName = dogBreedListResponse.body()?.message?.keys?.toList()?.random()
            val dogBreedTwoName = dogBreedListResponse.body()?.message?.keys?.toList()?.random()
            val dogBreedThreeName = dogBreedListResponse.body()?.message?.keys?.toList()?.random()
            val dogBreedFourName = dogBreedListResponse.body()?.message?.keys?.toList()?.random()
            val dogBreedFiveName = dogBreedListResponse.body()?.message?.keys?.toList()?.random()
            val dogBreedSixName = dogBreedListResponse.body()?.message?.keys?.toList()?.random()

            val dogBreedOneImageDeferred = async {
                dogBreedOneName?.let { dogsResponse.getImageByUrlAsync(it).execute() }
            }
            val dogBreedTwoImageDeferred = async {
                dogBreedTwoName?.let { dogsResponse.getImageByUrlAsync(it).execute() }
            }
            val dogBreedThreeImageDeferred = async {
                dogBreedThreeName?.let { dogsResponse.getImageByUrlAsync(it).execute() }
            }
            val dogBreedFourImageDeferred = async {
                dogBreedFourName?.let { dogsResponse.getImageByUrlAsync(it).execute() }
            }
            val dogBreedFiveImageDeferred = async {
                dogBreedFiveName?.let { dogsResponse.getImageByUrlAsync(it).execute() }
            }
            val dogBreedSixImageDeferred = async {
                dogBreedSixName?.let { dogsResponse.getImageByUrlAsync(it).execute() }
            }

            val dogBreedOne = dogBreedOneImageDeferred.await()
            val dogBreedTwo = dogBreedTwoImageDeferred.await()
            val dogBreedThree = dogBreedThreeImageDeferred.await()
            val dogBreedFour = dogBreedFourImageDeferred.await()
            val dogBreedFive = dogBreedFiveImageDeferred.await()
            val dogBreedSix = dogBreedSixImageDeferred.await()

            if (dogBreedOne?.isSuccessful!!) list.add(Dog(dogBreedOneName?.replaceFirstChar {
                if (it.isLowerCase()) it.titlecase(
                    Locale.getDefault()
                ) else it.toString()
            }, dogBreedOne.body()?.message))
            if (dogBreedTwo?.isSuccessful!!) list.add(Dog(dogBreedTwoName?.replaceFirstChar {
                if (it.isLowerCase()) it.titlecase(
                    Locale.getDefault()
                ) else it.toString()
            }, dogBreedTwo.body()?.message))
            if (dogBreedThree?.isSuccessful!!) list.add(Dog(dogBreedThreeName?.replaceFirstChar {
                if (it.isLowerCase()) it.titlecase(
                    Locale.getDefault()
                ) else it.toString()
            }, dogBreedThree.body()?.message))
            if (dogBreedFour?.isSuccessful!!) list.add(Dog(dogBreedFourName?.replaceFirstChar {
                if (it.isLowerCase()) it.titlecase(
                    Locale.getDefault()
                ) else it.toString()
            }, dogBreedFour.body()?.message))
            if (dogBreedFive?.isSuccessful!!) list.add(Dog(dogBreedFiveName?.replaceFirstChar {
                if (it.isLowerCase()) it.titlecase(
                    Locale.getDefault()
                ) else it.toString()
            }, dogBreedFive.body()?.message))
            if (dogBreedSix?.isSuccessful!!) list.add(Dog(dogBreedSixName?.replaceFirstChar {
                if (it.isLowerCase()) it.titlecase(
                    Locale.getDefault()
                ) else it.toString()
            }, dogBreedSix.body()?.message))

            GeneralResult.SuccessGeneric(list)
        } else {
            GeneralResult.Error("Something went wrong")
        }

    }
}

interface IDogsRepository {

    suspend fun getListOfDogs(): Results<List<Dog>>

    suspend fun getSixDogs(): GeneralResult

}



