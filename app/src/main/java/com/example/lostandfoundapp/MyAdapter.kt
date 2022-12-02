package com.example.lostandfoundapp

import android.location.Location
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text

class MyAdapter(private val userList : ArrayList<data_lost>) : RecyclerView.Adapter<MyAdapter.MyViewHolder> (){


    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
      val itemView = LayoutInflater.from(parent.context).inflate(R.layout.items_list, parent,false )
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val user : data_lost = userList[position]
        holder.Item.text=user.Item
        holder.Description.text=user.Description
        holder.Location.text=user.Location
        holder.Date.text=user.Date
        holder.Name.text=user.Name
        holder.Contact.text=user.Contact
        holder.Email.text=user.Email
    }

        public class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val Item: TextView = itemView.findViewById(R.id.lost_item)
         val Description: TextView=itemView.findViewById(R.id.lost_desc)
            val Location: TextView=itemView.findViewById(R.id.lost_loc)
            val Date: TextView=itemView.findViewById(R.id.lost_date)
            val Name: TextView=itemView.findViewById(R.id.lost_name)
            val Contact: TextView=itemView.findViewById(R.id.lost_mob)
            val Email: TextView=itemView.findViewById(R.id.lost_email)
        }
}