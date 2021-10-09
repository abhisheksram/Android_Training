package com.example.androidtraining.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidtraining.data.models.DogsBreed
import com.example.androidtraining.data.models.DogsImages
import com.example.androidtraining.repository.DogsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DogsViewModel constructor(private val repository: DogsRepository) : ViewModel() {

    private val imageData = MutableLiveData<List<DogsImages>>()
    private val breedData = MutableLiveData<List<DogsBreed>>()

    var job: Job? = null

    fun loadDogsImages(breedName : String) {

        job = viewModelScope.launch {
            val response = repository.getDogsImages(breedName)
            withContext(Dispatchers.IO) {

                imageData.postValue(listOf(response!!))
            }

        }
    }

    fun loadDogsBreeds() {

        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val response = repository.getDogsBreed()
                breedData.postValue(listOf(response!!))
            }
        }
    }

}