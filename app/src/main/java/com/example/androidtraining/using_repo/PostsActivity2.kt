package com.example.androidtraining.using_repo

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidtraining.R
import com.example.androidtraining.adapters.PostsAdapter
import com.example.androidtraining.result.Result
import com.example.androidtraining.view_models.MyViewModel2
import com.example.androidtraining.view_models.MyViewModelFactory


class PostsActivity2 : AppCompatActivity() {

    lateinit var adapter: PostsAdapter
    lateinit var recyclerView: RecyclerView
    private val postsViewModel by viewModels<MyViewModel2>{ MyViewModelFactory() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_posts)

        val actionBar = supportActionBar!!
        actionBar.title = "Posts"

        postsViewModel.getPosts()
        getPostsObserver()

        adapter = PostsAdapter()
        recyclerView = findViewById(R.id.rvPosts)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

       }
    private fun getPostsObserver(){

        postsViewModel.postsResult.observe(this, {
            when(it){
                is Result.Success->{ adapter.postList = it.value.body()?.toMutableList() }
                is Result.Failure->{Toast.makeText(this, it.message,Toast.LENGTH_SHORT).show()}
            }

        })

    }

}