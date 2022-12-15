package com.example.lostandfoundapp

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

val db_found= Firebase.firestore
class Found : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_found)
        val newIntent = getIntent()
        val USERNAME = newIntent.getStringExtra("USERNAME")
        var found_item=findViewById<EditText>(R.id.data_found_item)
        var found_description=findViewById<EditText>(R.id.data_found_desc)
        var found_location=findViewById<EditText>(R.id.data_found_loc)
        var found_date=findViewById<EditText>(R.id.data_found_date)

        findViewById<Button>(R.id.found_submit).setOnClickListener{
            val item=hashMapOf(
                "Item" to found_item.getText().toString(),
                "Description" to found_description.getText().toString(),
                "Location" to found_location.getText().toString(),
                "Date" to found_date.getText().toString(),
                "Email" to USERNAME.toString()
            )
            db_lost.collection("found")
                .add(item)
                .addOnSuccessListener {  documentReference ->
                    Log.d(ContentValues.TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
                }
                .addOnFailureListener { e ->
                    Log.w(ContentValues.TAG, "Error adding document", e)
                }

        }
        findViewById<Button>(R.id.buttonLoadPicture).setOnClickListener {
            val intent =Intent(this,storage2::class.java)
            startActivity(intent)
        }

    }}