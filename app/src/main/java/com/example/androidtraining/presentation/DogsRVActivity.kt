package com.example.androidtraining.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidtraining.R
import com.example.androidtraining.adapters.DogsAdapter
import com.example.androidtraining.data.models.Dog
import com.example.androidtraining.view_models.DogsVMFactory
import com.example.androidtraining.view_models.DogsViewModel

class DogsRVActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    private val viewModel by viewModels<DogsViewModel>{ DogsVMFactory() }
    private val adapter = DogsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dogs_rvactivity)
        val actionBar = supportActionBar!!
        actionBar.title = "Dogs RecyclerView"

        recyclerView = findViewById(R.id.rvDogsActivity)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        viewModel.loadDogList()

        viewModel.dogList.observe(this, Observer {
            val list = it as List<Dog>
            adapter.dogList= list.toMutableList()
        })
    }

}