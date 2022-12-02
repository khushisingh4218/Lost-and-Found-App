package com.example.lostandfoundapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.*
import kotlinx.coroutines.newFixedThreadPoolContext

class Feed : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var userArrayList: ArrayList<data_lost>
    private lateinit var myAdapter: ListAdapter
    private lateinit var db: FirebaseFirestore
    private var USERNAME:String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed)
        var newintent = getIntent()
        USERNAME = newintent.getStringExtra("USERNAME").toString()

        recyclerView = findViewById(R.id.feed_recycler)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        userArrayList = arrayListOf()
        myAdapter = ListAdapter(this,userArrayList)

        recyclerView.adapter = myAdapter
        EventChangeListener()
    }

    private fun  EventChangeListener(){
        db= FirebaseFirestore.getInstance()
        db.collection("lost").
        addSnapshotListener(object : EventListener<QuerySnapshot>{
            override fun onEvent(
                value: QuerySnapshot?,
                error: FirebaseFirestoreException?) {
                if(error != null){
                    Log.e("Firestore error",error.message.toString())
                    return
                }
                for(dc : DocumentChange in value?.documentChanges!!){
                    if(dc.type==DocumentChange.Type.ADDED){
                        if(dc.document.toObject(data_lost::class.java).Email==USERNAME){
                            userArrayList.add(dc.document.toObject(data_lost::class.java))

                        }


                    }

                }
                myAdapter.notifyDataSetChanged()
            }

        })

        db.collection("found").
        addSnapshotListener(object : EventListener<QuerySnapshot>{
            override fun onEvent(
                value: QuerySnapshot?,
                error: FirebaseFirestoreException?) {
                if(error != null){
                    Log.e("Firestore error",error.message.toString())
                    return
                }
                for(dc : DocumentChange in value?.documentChanges!!){
                    if(dc.type==DocumentChange.Type.ADDED){
                        if(dc.document.toObject(data_lost::class.java).Email==USERNAME){
                            userArrayList.add(dc.document.toObject(data_lost::class.java))

                        }


                    }

                }

                myAdapter.notifyDataSetChanged()
            }

        })




    }
}