package com.example.lostandfoundapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.checkerframework.checker.index.qual.GTENegativeOne

class MyAdapter2 (private val userList : ArrayList<data_found>) : RecyclerView.Adapter<MyAdapter2.MyViewHolder2> (){


    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder2 {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.items_list, parent,false )
        return MyViewHolder2(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder2, position: Int) {
        val user : data_found = userList[position]
        holder.Item.text=user.Item
        holder.Description.text=user.Description
        holder.Location.text=user.Location
        holder.Date.text=user.Date
        holder.clmbut.visibility = View.GONE
    }

    public class MyViewHolder2(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val Item: TextView = itemView.findViewById(R.id.lost_item)
        val Description: TextView=itemView.findViewById(R.id.lost_desc)
        val Location: TextView=itemView.findViewById(R.id.lost_loc)
        val Date: TextView=itemView.findViewById(R.id.lost_date)
        val clmbut: Button = itemView.findViewById(R.id.claim)
    }
}