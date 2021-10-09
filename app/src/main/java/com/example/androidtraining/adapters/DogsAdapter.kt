package com.example.androidtraining.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.androidtraining.R
import com.example.androidtraining.data.models.DogsBreed
import com.example.androidtraining.data.models.DogsImages
import com.squareup.picasso.Picasso

class DogsAdapter : RecyclerView.Adapter<DogsAdapter.DogsViewHolder>() {

    private var dogsBreed: MutableList<DogsBreed>? =null
    private var dogsImages: MutableList<DogsImages>? = null

    fun setDogsImages(dogsImages: List<DogsImages>) {
        this.dogsImages = dogsImages.toMutableList()
        notifyDataSetChanged()
    }

    fun setDogsBreed(dogsBreed: List<DogsBreed>) {
        this.dogsBreed = dogsBreed.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogsViewHolder {
        return DogsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_dogs, parent, false))
    }

    override fun onBindViewHolder(holder: DogsViewHolder, position: Int) {
        holder.bind(dogsImages?.get(position), dogsBreed?.get(position))
    }

    override fun getItemCount() = dogsImages?.size ?: 0

    inner class DogsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val imageDogs: ImageView = itemView.findViewById(R.id.imageRVDog)
        private val tvDogsBreed : TextView = itemView.findViewById(R.id.tvRVDog)
        fun bind(images: DogsImages?, dogsBreed: DogsBreed?) {

            tvDogsBreed.text = dogsBreed?.message.toString()
            Picasso.get().load(images?.message).resize(600, 400).into(imageDogs)
/*
            Glide.with(itemView)
                .load(data?.url)
                .override(600,400)
                .placeholder(R.drawable.image_landscape)
                .error(R.drawable.image_landscape)
                .into(imageView)

 */
        }
    }
}