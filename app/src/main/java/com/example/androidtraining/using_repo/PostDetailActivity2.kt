package com.example.androidtraining.using_repo

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidtraining.R
import com.example.androidtraining.adapters.PostDetailsAdapter
import com.example.androidtraining.common.Constants
import com.example.androidtraining.data.result.Result
import com.example.androidtraining.view_models.*

class PostDetailActivity2 : AppCompatActivity() {

    lateinit var adapter: PostDetailsAdapter
    private lateinit var layoutManager: LinearLayoutManager
    lateinit var recyclerView : RecyclerView
    private val commentsViewModel by viewModels<MyViewModel2>{MyViewModelFactory()}
    private var postID : Long = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_detail)

        getPostData()
        commentsViewModel.getPostComments(postID)
        getCommentsObserver()

        adapter = PostDetailsAdapter()

        recyclerView  = findViewById(R.id.rvPostDetail)
        layoutManager = LinearLayoutManager(this)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = layoutManager
    }

    @SuppressLint("SetTextI18n")
    private fun getPostData() {
        val intent = intent
        val id = intent.getStringExtra(Constants.Post.id)
        val title = intent.getStringExtra(Constants.Post.title)
        val body =  intent.getStringExtra(Constants.Post.body)

        val actionBar = supportActionBar!!
        actionBar.title = "Post: $id Details"

        val tvPostID: TextView = findViewById(R.id.tvPostDetailID)
        val tvPostTitle: TextView = findViewById(R.id.tvPostDetailTitle)
        val tvPostBody: TextView = findViewById(R.id.tvPostDetailBody)

        tvPostID.text = "$id."
        tvPostTitle.text = "Title: $title"
        tvPostBody.text = "Description: $body"

        postID = Constants.Post.postID

    }


    private fun getCommentsObserver() {

        commentsViewModel.commentResult.observe(this, {
            when(it){
                is Result.Success->{ adapter.comments = it.value.body()?.toMutableList() }
                is Result.Failure->{Toast.makeText(this, it.message,Toast.LENGTH_SHORT).show()}
            }

        })

    }

}