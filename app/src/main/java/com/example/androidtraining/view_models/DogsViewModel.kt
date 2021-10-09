package com.example.androidtraining.view_models

import androidx.lifecycle.*
import com.example.androidtraining.data.models.Dog
import com.example.androidtraining.repository.IDogsRepository
import com.example.androidtraining.data.result.GeneralResult
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext


class DogsViewModel constructor(private val dogsRepository: IDogsRepository): ViewModel(), CoroutineScope {

    private val parentJob = SupervisorJob()

    private val _dogList = MutableLiveData<List<Dog>>()
    private val _topDogsAsync = MutableLiveData<List<Dog>>()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + parentJob

    val dogList: LiveData<List<Dog>>
        get() = _dogList

    val sixDogs: LiveData<List<Dog>>
        get() = _topDogsAsync


    fun getSixDogsData(): LiveData<GeneralResult> = liveData {

        while (true) {
            delay(5000L)
            val topSixDogs = dogsRepository.getSixDogs()
            emit(topSixDogs)

        }
    }


    fun loadDogList() {
        parentJob.cancelChildren()
//        _dogList.value = emptyList()
        launch {
            val result = runCatching { dogsRepository.getListOfDogs() }
            result.onSuccess {
                _dogList.value = it.value!!
            }.onFailure {
            }
        }
    }

    val liveDataResult = liveData {
        emitSource(getSixDogsData())
    }

    override fun onCleared() {
        super.onCleared()
        parentJob.cancelChildren()
    }

}