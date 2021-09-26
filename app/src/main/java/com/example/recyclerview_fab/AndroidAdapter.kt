package com.example.recyclerview_fab


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import kotlin.collections.ArrayList


class AndroidAdapter( val callback: (MyData?, Int) -> Unit, val showDeleteAlert:
    (Int) -> Unit ) :
    RecyclerView.Adapter<AndroidAdapter.AndroidViewHolder>() {

    var androidList:ArrayList<MyData>?=null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AndroidViewHolder {
        val itemView: View =
            LayoutInflater.from(parent.context).inflate(R.layout.version_list, parent, false)
        return AndroidViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: AndroidViewHolder, position: Int) {
        holder.bind(androidList?.get(position)!!)

    }

    override fun getItemCount(): Int {
        return androidList?.size!!
    }


    inner class AndroidViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        init {
            val btnDelete: ImageButton = itemView.findViewById(R.id.btnDelete)

                itemView.setOnClickListener {
                    callback.invoke(androidList?.get(adapterPosition), adapterPosition)
                }
                btnDelete.setOnClickListener {
                    showDeleteAlert.invoke(adapterPosition)
                }

        }

        fun bind(myData: MyData) {
            val lblVer: TextView = itemView.findViewById(R.id.lblVer)
            val lblName: TextView = itemView.findViewById(R.id.lblName)
            val lblVersion: TextView = itemView.findViewById(R.id.lblVersion)
            val lblDate: TextView = itemView.findViewById(R.id.lblDate)
            val lblSDK: TextView = itemView.findViewById(R.id.lblSDK)
            val lblDesc: TextView = itemView.findViewById(R.id.lblDesc)

            lblVer.text = myData.ver
            lblName.text = myData.name
            lblVersion.text = myData.version
            lblSDK.text = myData.sdk
            lblDesc.text = myData.desc
            lblDate.text = myData.date


        }

    }

}



