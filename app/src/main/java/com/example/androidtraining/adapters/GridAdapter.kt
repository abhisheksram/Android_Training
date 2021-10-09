package com.example.androidtraining.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.androidtraining.R
import com.example.androidtraining.data.models.ImageData
import com.squareup.picasso.Picasso

class GridAdapter : RecyclerView.Adapter<GridAdapter.GridViewHolder>() {

    var images: MutableList<ImageData>? = null
        set(value) {
            field = value?.toMutableList()
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GridViewHolder {
        return GridViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_images, parent, false))
    }

    override fun onBindViewHolder(holder: GridViewHolder, position: Int) {
        holder.bind(images?.get(position))
    }

    override fun getItemCount() = images?.size ?: 0

    inner class GridViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val imageView: ImageView = itemView.findViewById(R.id.imageGrid)

        fun bind(data: ImageData?) {
                Picasso.get().load(data?.url).resize(600, 400).into(imageView)
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