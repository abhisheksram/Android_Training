
package com.example.androidtraining.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidtraining.R
import com.example.androidtraining.adapters.PostDetailsAdapter
import com.example.androidtraining.constants.Constants
import com.example.androidtraining.view_models.MyViewModel

class PostDetailActivity : AppCompatActivity() {


    lateinit var adapter: PostDetailsAdapter
    private lateinit var layoutManager: LinearLayoutManager
    lateinit var recyclerView : RecyclerView
    private var postID : Long = 1
    private lateinit var viewModelFactory: MyViewModel.ViewModelFactory
    private val myViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(MyViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_detail)

        getPostData()
        viewModelFactory = MyViewModel.ViewModelFactory()
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

        myViewModel.getPostComments(postID)
        myViewModel.comments.observe(this,  {
            if (it != null) {
                adapter.comments = it.toMutableList()

            }

        })
        myViewModel.errorMessage.observe(this,  {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })

    }
}

