package com.example.lostandfoundapp

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView

class Claim_part2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_claim_part2)
        val newIntent = getIntent()
        val USERNAME:String? = newIntent.getStringExtra("USERNAME")
        Log.d(ContentValues.TAG,"CLAIM 2 USERNAME is ${USERNAME}")
        val contact = newIntent.getStringExtra("contact")
        val name = newIntent.getStringExtra("name")

        findViewById<TextView>(R.id.textView).setText("Claimed By: "+USERNAME)
        findViewById<TextView>(R.id.textView2).setText("Name: "+name)
        findViewById<TextView>(R.id.textView3).setText("Email: "+USERNAME)
        findViewById<TextView>(R.id.textView4).setText("Contact No.: "+contact)

    }
}