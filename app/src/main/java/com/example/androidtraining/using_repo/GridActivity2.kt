package com.example.androidtraining.using_repo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.example.androidtraining.result.Result
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidtraining.R
import com.example.androidtraining.adapters.GridAdapter
import com.example.androidtraining.view_models.*

class GridActivity2 : AppCompatActivity() {

    lateinit var adapter: GridAdapter
    private lateinit var layoutManager: LinearLayoutManager
    lateinit var recyclerView: RecyclerView
    private lateinit var gridLayoutManager: GridLayoutManager
    private val imageViewModel by viewModels<MyViewModel2>{MyViewModelFactory()}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grid)

        val actionBar = supportActionBar!!
        actionBar.title = "Images"

        imageViewModel.getImages()
        getImageObserver()

        gridLayoutManager = GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false)
        gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return if (position % 3 == 0) 2 else 1
            }
        }
        recyclerView = findViewById(R.id.rvGrid)
        layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        adapter = GridAdapter()

        recyclerView.adapter = adapter
        recyclerView.layoutManager = gridLayoutManager

    }

    private fun getImageObserver(){

        imageViewModel.imageResult.observe(this, {
            when(it){
                is Result.Success->{ adapter.images = it.value.body()?.toMutableList() }
                is Result.Failure->{Toast.makeText(this, it.message,Toast.LENGTH_SHORT).show()}
            }

        })

    }

}