package com.example.androidtraining.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.androidtraining.R
import com.example.androidtraining.data.models.Dog
import com.squareup.picasso.Picasso
import java.util.*

class DogsAdapter : RecyclerView.Adapter<DogsAdapter.DogsViewHolder>() {

    var dogList: MutableList<Dog>? = null
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value?.toMutableList()
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogsViewHolder {
        return DogsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_dogs, parent, false))
    }

    override fun onBindViewHolder(holder: DogsViewHolder, position: Int) {
        holder.bind(dogList?.get(position))
    }

    override fun getItemCount()= dogList?.size ?: 0


    inner class DogsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val imageDogs: ImageView = itemView.findViewById(R.id.imageRVDog)
        private val tvDogsBreed : TextView = itemView.findViewById(R.id.tvRVDog)

        fun bind(dog: Dog?) {
            tvDogsBreed.text = dog?.breed?.replaceFirstChar {
                if (it.isLowerCase()) it.titlecase(
                    Locale.ROOT
                ) else it.toString()
            }
            //Picasso.get().load(dog?.imageUrl).resize(600, 400).into(imageDogs)

            Glide.with(itemView)
                .load(dog?.imageUrl)
                .override(600,400)
                .error(R.drawable.image_landscape)
                .into(imageDogs)

        }
    }


}