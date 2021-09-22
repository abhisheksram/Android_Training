package com.example.views

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AndroidAdapter(private val androidList: ArrayList<MyData>) :
    RecyclerView.Adapter<AndroidAdapter.AndroidViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AndroidViewHolder {
        val itemView: View =  LayoutInflater.from(parent.context).inflate(R.layout.version_list, parent, false)
        return AndroidViewHolder(itemView)  }

    override fun onBindViewHolder(holder: AndroidViewHolder, position: Int) {
        val item = androidList[position]
        holder.lblVer.text = item.ver
        holder.lblName.text = item.name
        holder.lblVersion.text = item.version
        holder.lblSDK.text = item.sdk
        holder.lblDesc.text = item.desc
        holder.lblDate.text = item.date
    }

    override fun getItemCount(): Int {
                return androidList.size
    }

    class AndroidViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val lblVer: TextView = itemView.findViewById(R.id.lblVer)
        val lblName: TextView = itemView.findViewById(R.id.lblName)
        val lblVersion: TextView = itemView.findViewById(R.id.lblVersion)
        val lblDate: TextView = itemView.findViewById(R.id.lblDate)
        val lblSDK : TextView = itemView.findViewById(R.id.lblSDK)
        val lblDesc: TextView = itemView.findViewById(R.id.lblDesc)

    }

}