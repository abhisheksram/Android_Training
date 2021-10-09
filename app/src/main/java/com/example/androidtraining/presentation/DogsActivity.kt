package com.example.androidtraining.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.androidtraining.databinding.ActivityDogsBinding


private lateinit var binding: ActivityDogsBinding
class DogsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDogsBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}