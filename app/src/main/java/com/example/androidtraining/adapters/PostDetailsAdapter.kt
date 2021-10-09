package com.example.androidtraining.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.androidtraining.R
import com.example.androidtraining.data.models.MyComments

class PostDetailsAdapter :  RecyclerView.Adapter<PostDetailsAdapter.PostDetailsViewHolder>() {

    var comments:MutableList<MyComments>?=null

        set(value) {
            field = value?.toMutableList()
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostDetailsViewHolder {
    return PostDetailsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_post_comments, parent, false))
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: PostDetailsViewHolder, position: Int) {
        holder.bind(comments?.get(position))
    }

    override fun getItemCount()=  comments?.size ?:0

    class PostDetailsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        @SuppressLint("SetTextI18n")
        fun bind(data: MyComments?) {
            with(itemView){
                val tvCommentsName: TextView = itemView.findViewById(R.id.tvCommentsName)
                val tvCommentsEmail: TextView = itemView.findViewById(R.id.tvCommentsEmail)
                val tvCommentsBody: TextView = itemView.findViewById(R.id.tvCommentsBody)

                tvCommentsName.text = "Name: ${data?.name}"
                tvCommentsEmail.text = "E-Mail: ${data?.email}"
                tvCommentsBody.text = "Comment: ${data?.body}"
            }
        }
    }
}


