package com.example.androidtraining.util

import android.content.Context
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import kotlin.coroutines.CoroutineContext

fun Context.showToast(msg : String){
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}

fun loadImage(context: Context, url: String, imageView: ImageView, radius: Int = 1) {
    Glide.with(context)
        .load(url)
        .fitCenter()
        .into(imageView)
}

fun removeCache(context: Context) {
    Glide.get(context).clearMemory()
}