package com.example.androidtraining.view_models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.androidtraining.constants.Constants
import com.example.androidtraining.data.remote.RetrofitInterface
import com.example.androidtraining.data.remote.RetrofitPostCommentsInterface
import com.example.androidtraining.data.remote.RetrofitPostInterface
import com.example.androidtraining.data.models.ImageData
import com.example.androidtraining.data.models.MyComments
import com.example.androidtraining.data.models.MyPostsData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyViewModel : ViewModel() {

    var postLiveData = MutableLiveData<List<MyPostsData>>()
    val errorMessage = MutableLiveData<String>()
    var comments = MutableLiveData<List<MyComments>>()
    var imageLiveData = MutableLiveData<List<ImageData>>()

    fun getImages() {
        val retrofitInterface: RetrofitInterface = Constants.Common.retrofitService
        val call = retrofitInterface.getImages()
        call.enqueue(object : Callback<List<ImageData>> {
            override fun onResponse(
                call: Call<List<ImageData>>,
                response: Response<List<ImageData>>
            ) {
                imageLiveData.postValue(response.body())
            }

            override fun onFailure(call: Call<List<ImageData>>, t: Throwable) {
                errorMessage.postValue(t.message)
            }
        })
    }

    fun getPosts() {
        val retrofitInterface: RetrofitPostInterface = Constants.Common.retrofitServicePosts
        val call = retrofitInterface.getPostsItem()
        call.enqueue(object : Callback<List<MyPostsData>> {
            override fun onResponse(
                call: Call<List<MyPostsData>>,
                response: Response<List<MyPostsData>>
            ) {
                postLiveData.postValue(response.body())
            }

            override fun onFailure(call: Call<List<MyPostsData>>, t: Throwable) {
                errorMessage.postValue(t.message)
            }
        })
    }

    fun getPostComments(postId: Long) {

        val retrofitInterface: RetrofitPostCommentsInterface =
            Constants.Common.retrofitServiceComments
        val call = retrofitInterface.getComments(postId)
        call.enqueue(object : Callback<List<MyComments>> {
            override fun onResponse(
                call: Call<List<MyComments>>,
                response: Response<List<MyComments>>
            ) {
                comments.postValue(response.body())
            }

            override fun onFailure(call: Call<List<MyComments>>, t: Throwable) {
                errorMessage.postValue(t.message)
            }
        })
    }

    class ViewModelFactory : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return if (modelClass.isAssignableFrom(MyViewModel::class.java)) {
                MyViewModel() as T
            } else {
                throw IllegalArgumentException("ViewModel not found")
            }
        }
    }
}