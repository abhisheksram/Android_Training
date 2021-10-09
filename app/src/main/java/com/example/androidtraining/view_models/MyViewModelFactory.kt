package com.example.androidtraining.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.androidtraining.data.remote.RetrofitResponse
import com.example.androidtraining.repository.IMyRepository
import com.example.androidtraining.repository.MyRepository

class MyViewModelFactory: ViewModelProvider.Factory {

    init {
        getInstance()
    }
    companion object {
        @Volatile
        private var INSTANCE:IMyRepository?=null
    }
    fun getInstance() =
        INSTANCE ?: synchronized(MyViewModelFactory::class.java){
            INSTANCE?:MyRepository(
                RetrofitResponse.getClient()
            ).also { INSTANCE = it }

            fun destroyInstance(){
                INSTANCE = null
            }
        }


    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(IMyRepository::class.java).newInstance(INSTANCE)
    }

}

