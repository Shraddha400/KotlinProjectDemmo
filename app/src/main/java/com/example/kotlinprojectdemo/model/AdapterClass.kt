package com.example.kotlinprojectdemo.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinprojectdemo.R
import com.example.kotlinprojectdemo.data.CommentResponseItem

class AdapterClass (
    private val exampleList: List<CommentResponseItem>,

) : RecyclerView.Adapter<AdapterClass.ExampleViewHolder>() {
    inner class ExampleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val textId: TextView = itemView.findViewById(R.id.id)
        val textName: TextView = itemView.findViewById(R.id.name)
        val textEmail: TextView = itemView.findViewById(R.id.email)
        val textPostId: TextView = itemView.findViewById(R.id.postId)
        val textBody: TextView = itemView.findViewById(R.id.body)




    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExampleViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_view,parent,false)
        return  ExampleViewHolder(itemView)    }

    override fun onBindViewHolder(holder: ExampleViewHolder, position: Int) {
        val currentItem = exampleList[position]
        holder.textId.text = currentItem.id.toString()
        holder.textName.text = currentItem.name
        holder.textEmail.text = currentItem.email
        holder.textPostId.text = currentItem.postId.toString()
        holder.textBody.text = currentItem.body
    }

    override fun getItemCount(): Int {
        return  exampleList.size
    }
}