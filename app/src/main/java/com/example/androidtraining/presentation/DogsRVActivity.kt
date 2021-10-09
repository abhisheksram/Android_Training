package com.example.androidtraining.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.example.androidtraining.R
import com.example.androidtraining.adapters.DogsAdapter
import com.example.androidtraining.data.remote.DogsResponse
import com.example.androidtraining.databinding.ActivityDogsBinding
import com.example.androidtraining.databinding.ActivityDogsRvactivityBinding
import com.example.androidtraining.repository.DogsRepository
import com.example.androidtraining.result.Result
import com.example.androidtraining.view_models.DogsVMFactory
import com.example.androidtraining.view_models.DogsViewModel
import com.example.androidtraining.view_models.MyViewModel2
import com.example.androidtraining.view_models.MyViewModelFactory

private lateinit var binding: ActivityDogsRvactivityBinding

class DogsRVActivity : AppCompatActivity() {

    private val viewModel by viewModels<DogsViewModel>{ DogsVMFactory() }
    private val adapter = DogsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDogsRvactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvDogsActivity.adapter = adapter

        viewModel.loadDogsBreeds()

    }
}