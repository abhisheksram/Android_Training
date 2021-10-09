package com.example.androidtraining.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.androidtraining.data.remote.DogsResponse
import com.example.androidtraining.repository.DogsRepository
import com.example.androidtraining.repository.IDogsRepository

class DogsVMFactory : ViewModelProvider.Factory {

    init {
        getInstance()
    }

    companion object {
        @Volatile
        private var INSTANCE: IDogsRepository? = null

        fun getInstance() =
            INSTANCE ?: synchronized (DogsVMFactory::class.java) {
                INSTANCE ?: DogsRepository(DogsResponse.apiDogs()).also { INSTANCE = it }
            }

        fun destroyInstance() {
            INSTANCE = null
        }
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(IDogsRepository::class.java).newInstance(INSTANCE)
    }

}

