package com.example.androidtraining.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.androidtraining.repository.DogsRepository

class DogsVMFactory : ViewModelProvider.Factory {

    init {
        getInstance()
    }

    companion object {
        @Volatile
        private var INSTANCE: DogsRepository? = null

        fun getInstance() =
            INSTANCE ?: synchronized (DogsVMFactory::class.java) {
                INSTANCE ?: DogsRepository().also { INSTANCE = it }
            }

        fun destroyInstance() {
            INSTANCE = null
        }
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(DogsRepository::class.java).newInstance(INSTANCE)
    }

}

