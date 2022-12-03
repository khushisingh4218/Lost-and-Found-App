package com.example.lostandfoundapp

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.*
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.newFixedThreadPoolContext

class Lost_list : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var userArrayList: ArrayList<data_lost>
    private lateinit var myAdapter: MyAdapter
    private lateinit var db: FirebaseFirestore
    public var USERNAME:String? = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lost_list)
        val newIntent = getIntent()
        USERNAME= newIntent.getStringExtra("USERNAME")
        Log.d(TAG, "user is ${USERNAME}")

        recyclerView = findViewById(R.id.recycle_view_lost)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        userArrayList = arrayListOf()
        myAdapter = MyAdapter(this,userArrayList)

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
                    if(dc.type==DocumentChange.Type.ADDED ){
                        if(dc.document.data.get("Settled")=="False"){

                            Log.d(TAG,"${dc.document.toObject(data_lost::class.java).Item.toString()} + ${dc.document.toObject(data_lost::class.java).Settled}")

                            userArrayList.add(dc.document.toObject(data_lost::class.java))
                        }


                    }

                }
                myAdapter.notifyDataSetChanged()
            }

        })

    }
}