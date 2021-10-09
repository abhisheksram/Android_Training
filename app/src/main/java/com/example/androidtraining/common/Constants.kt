package com.example.androidtraining.common

import com.example.androidtraining.data.remote.RetrofitClient
import com.example.androidtraining.data.remote.RetrofitInterface
import com.example.androidtraining.data.remote.RetrofitPostCommentsInterface
import com.example.androidtraining.data.remote.RetrofitPostInterface


object Constants {

        object SharedPref{
            const val  myData = "myData"
        }

    object Pref{
        const val fragment1 = "data1"
        const val fragment2 = "data2"
        //const val slide = "Slide"
    }

    object Nav{
        const val home = "home"
        const val search = "search"
        const val orders = "orders"
        const val profile = "profile"

    }

    object Common {

        const val baseURL = "https://jsonplaceholder.typicode.com/"
        val retrofitService: RetrofitInterface = RetrofitClient().getClient(baseURL).create(
            RetrofitInterface::class.java)

        val retrofitServicePosts: RetrofitPostInterface = RetrofitClient().getClient(baseURL).create(
            RetrofitPostInterface::class.java)

        var retrofitServiceComments: RetrofitPostCommentsInterface =
            RetrofitClient().getClient(baseURL).create(RetrofitPostCommentsInterface::class.java)

    }

    object Post{
        const val id ="name"
        const val title = "title"
        const val body = "body"
        var postID : Long = 0L

    }

    object Notification{
        const val channelID ="Channel ID"
        const val channelName = "Channel Name"

    }

}