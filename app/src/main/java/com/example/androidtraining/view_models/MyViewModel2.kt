package com.example.androidtraining.view_models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidtraining.data.models.ImageData
import com.example.androidtraining.data.models.MyComments
import com.example.androidtraining.data.models.MyPostsData
import com.example.androidtraining.repository.IMyRepository
import com.example.androidtraining.result.Result
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyViewModel2(private val iMyRepository: IMyRepository) : ViewModel() {

    val imageResult = MutableLiveData<Result<Response<List<ImageData>>>>()
    val postsResult = MutableLiveData<Result<Response<List<MyPostsData>>>>()
    val commentResult = MutableLiveData<Result<Response<List<MyComments>>>>()

    fun getImages() {

        val call = iMyRepository.getImages()
        call.enqueue(object : Callback<List<ImageData>> {
            override fun onResponse(
                call: Call<List<ImageData>>,
                response: Response<List<ImageData>>
            ) {
                imageResult.value = Result.Success(response)

            }

            override fun onFailure(call: Call<List<ImageData>>, t: Throwable) {
                imageResult.value = Result.Failure(t.message)
            }
        })
    }

    fun getPosts() {
        val call = iMyRepository.getPostsItems()
        call.enqueue(object : Callback<List<MyPostsData>> {
            override fun onResponse(
                call: Call<List<MyPostsData>>,
                response: Response<List<MyPostsData>>
            ) {
                postsResult.value = Result.Success(response)

            }

            override fun onFailure(call: Call<List<MyPostsData>>, t: Throwable) {
                postsResult.value = Result.Failure(t.message)

            }
        })
    }

    fun getPostComments(postId: Long) {

        val call = iMyRepository.getComments(postId)
        call.enqueue(object : Callback<List<MyComments>> {
            override fun onResponse(
                call: Call<List<MyComments>>,
                response: Response<List<MyComments>>
            ) {
                commentResult.value = Result.Success(response)

            }

            override fun onFailure(call: Call<List<MyComments>>, t: Throwable) {
                commentResult.value = Result.Failure(t.message)

            }
        })
    }
}