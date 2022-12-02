package com.example.lostandfoundapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.*

class Found_list : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var userArrayList: ArrayList<data_found>
    private lateinit var myAdapter: MyAdapter2
    private lateinit var db: FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_found_list)

        recyclerView = findViewById(R.id.recycle_view_found)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        userArrayList = arrayListOf()
        myAdapter = MyAdapter2(userArrayList)

        recyclerView.adapter = myAdapter
        EventChangeListener()


    }
    private fun  EventChangeListener(){
        db= FirebaseFirestore.getInstance()
        db.collection("found").
        addSnapshotListener(object : EventListener<QuerySnapshot> {
            override fun onEvent(
                value: QuerySnapshot?,
                error: FirebaseFirestoreException?) {
                if(error != null){
                    Log.e("Firestore error",error.message.toString())
                    return
                }
                for(dc : DocumentChange in value?.documentChanges!!){
                    if(dc.type== DocumentChange.Type.ADDED){
                        userArrayList.add(dc.document.toObject(data_found::class.java))

                    }

                }
                myAdapter.notifyDataSetChanged()
            }

        })

    }
}