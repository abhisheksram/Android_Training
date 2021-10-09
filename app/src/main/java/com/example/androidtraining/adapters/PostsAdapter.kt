package com.example.androidtraining.adapters

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.androidtraining.common.Constants
import com.example.androidtraining.using_repo.PostDetailActivity2
import com.example.androidtraining.R
import com.example.androidtraining.data.models.MyPostsData

class PostsAdapter  :RecyclerView.Adapter<PostsAdapter.PostsViewHolder>() {

    var postList: MutableList<MyPostsData>? = null
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value?.toMutableList()
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup,viewType: Int): PostsViewHolder {
        return PostsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_posts, parent, false))
    }

    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) {
        holder.bind(postList?.get(position))

    }

    override fun getItemCount() = postList?.size ?: 0

    inner class PostsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        init {

            itemView.setOnClickListener {

                val intent = Intent(itemView.context, PostDetailActivity2::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                intent.putExtra(Constants.Post.id,postList?.get(adapterPosition)?.id.toString())
                intent.putExtra(Constants.Post.title,postList?.get(adapterPosition)?.title)
                intent.putExtra(Constants.Post.body,postList?.get(adapterPosition)?.body)
                //intent.putExtra(Constants.Post.postID,postList?.get(adapterPosition)?.id)
                itemView.context.startActivity(intent)

                Constants.Post.postID = postList!![adapterPosition].id
            }
        }

        fun bind(data: MyPostsData?) {
            with(itemView) {
                val tvPostID: TextView = itemView.findViewById(com.example.androidtraining.R.id.tvPostID)
                val tvPostTitle: TextView = itemView.findViewById(com.example.androidtraining.R.id.tvPostTitle)
                val tvPostBody: TextView = itemView.findViewById(com.example.androidtraining.R.id.tvPostBody)

                tvPostID.text = data?.id.toString()
                tvPostTitle.text = data?.title
                tvPostBody.text = data?.body
            }
        }
    }
}