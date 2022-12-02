package com.example.lostandfoundapp

import android.app.Activity
import android.content.Intent
import android.location.Location
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text

class ListAdapter(private val context: Feed, private val userList : ArrayList<data_lost>) : RecyclerView.Adapter<ListAdapter.ViewHolder> (){


    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.items_list, parent,false)

        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user : data_lost = userList[position]
        holder.Item.text=user.Item
        holder.Description.text=user.Description
        holder.Location.text=user.Location
        holder.Date.text=user.Date
        holder.claimButton.setOnClickListener{

            val bundle = Bundle()
            bundle.putString("name","Khushi Singh")
            val newIntent = Intent(context, Claim_Found::class.java)
            newIntent.putExtra("bundle",bundle)
            startActivity(context, newIntent,bundle)



        }

    }

    public class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val Item: TextView = itemView.findViewById(R.id.lost_item)
        val Description: TextView=itemView.findViewById(R.id.lost_desc)
        val Location: TextView=itemView.findViewById(R.id.lost_loc)
        val Date: TextView=itemView.findViewById(R.id.lost_date)
        val claimButton: Button = itemView.findViewById(R.id.claim)
    }
}