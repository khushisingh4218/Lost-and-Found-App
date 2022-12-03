package com.example.lostandfoundapp

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

val db_lost= Firebase.firestore

class Lost : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lost)
        val newIntent = getIntent()
        val USERNAME = newIntent.getStringExtra("USERNAME")

        var lost_item=findViewById<EditText>(R.id.data_lost_item)
        var lost_description=findViewById<EditText>(R.id.data_lost_desc)
        var lost_location=findViewById<EditText>(R.id.data_lost_loc)
        var lost_date=findViewById<EditText>(R.id.data_lost_date)

//        findViewById<Button>(R.id.lost_submit).setOnClickListener {
//            lost_item.get()
//
//        }
        findViewById<Button>(R.id.buttonLoadPicture).setOnClickListener {
            val intent =Intent(this,StorageActivity::class.java)
            startActivity(intent)
        }

        findViewById<Button>(R.id.lost_submit).setOnClickListener{
            val item=hashMapOf(
                "Item" to lost_item.getText().toString(),
                "Description" to lost_description.getText().toString(),
                "Location" to lost_location.getText().toString(),
                "Date" to lost_date.getText().toString(),
                "Email" to USERNAME.toString(),
                "Settled" to "False"
            )
            db_lost.collection("lost")
                .add(item)
                .addOnSuccessListener {  documentReference ->
                    Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
                }
                .addOnFailureListener { e ->
                    Log.w(TAG, "Error adding document", e)
                }

        }


    }}