package com.example.androidtraining.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.androidtraining.data.models.Dog
import com.example.androidtraining.databinding.ActivityDogsBinding
import com.example.androidtraining.data.result.GeneralResult
import com.example.androidtraining.view_models.DogsVMFactory
import com.example.androidtraining.view_models.DogsViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_dogs.*
import java.util.*

private lateinit var binding: ActivityDogsBinding

class DogsActivity : AppCompatActivity() {

    private val viewModel by viewModels<DogsViewModel>{ DogsVMFactory() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDogsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val actionBar = supportActionBar!!
        actionBar.title = "Dogs Activity"

        viewModel.getSixDogsData()
        loadSixDogs()

        viewModel.liveDataResult.observe(this, Observer {
            when (it) {
                is GeneralResult.SuccessGeneric<*> -> {
                    updateSixDogs(it.data as List<Dog>)
                }
                is GeneralResult.Error -> {
                }
            }
        })
    }

    private fun loadSixDogs(){

        viewModel.sixDogs.observe(this, Observer {
            it?.let { dog ->
                dog[0].let {dogs ->
                    tvDog1.text = dogs.breed
                    Picasso.get().load(dogs.imageUrl).resize(600, 400).into(imageDog1)
                }

                dog[1].let {dogs ->
                    tvDog2.text = dogs.breed
                    Picasso.get().load(dogs.imageUrl).resize(600, 400).into(imageDog2)
                }
                dog[2].let {dogs ->
                    tvDog3.text = dogs.breed
                    Picasso.get().load(dogs.imageUrl).resize(600, 400).into(imageDog3)
                }
                dog[3].let {dogs ->
                    tvDog4.text = dogs.breed
                    Picasso.get().load(dogs.imageUrl).resize(600, 400).into(imageDog4)
                }
                dog[4].let {dogs ->
                    tvDog5.text = dogs.breed
                    Picasso.get().load(dogs.imageUrl).resize(600, 400).into(imageDog5)
                }
                dog[5].let {dogs ->
                    tvDog6.text = dogs.breed
                    Picasso.get().load(dogs.imageUrl).resize(600, 400).into(imageDog6)
                }
            }
        })
    }

    private fun updateSixDogs(it: List<Dog>) {
        it.let { dog ->
            dog[0].let {dogs ->
                tvDog1.text = dogs.breed
                Picasso.get().load(dogs.imageUrl).resize(600, 400).into(imageDog1)
            }

            dog[1].let {dogs ->
                tvDog2.text = dogs.breed
                Picasso.get().load(dogs.imageUrl).resize(600, 400).into(imageDog2)
            }
            dog[2].let {dogs ->
                tvDog3.text = dogs.breed
                Picasso.get().load(dogs.imageUrl).resize(600, 400).into(imageDog3)
            }
            dog[3].let {dogs ->
                tvDog4.text = dogs.breed
                Picasso.get().load(dogs.imageUrl).resize(600, 400).into(imageDog4)
            }
            dog[4].let {dogs ->
                tvDog5.text = dogs.breed
                Picasso.get().load(dogs.imageUrl).resize(600, 400).into(imageDog5)
            }
            dog[5].let {dogs ->
                tvDog6.text = dogs.breed
                Picasso.get().load(dogs.imageUrl).resize(600, 400).into(imageDog6)
            }
        }
    }


}