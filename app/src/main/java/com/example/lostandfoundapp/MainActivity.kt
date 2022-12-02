package com.example.lostandfoundapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.register).setOnClickListener() {
            val regintent= Intent(this,Register::class.java)
            startActivity(regintent)
        }
        findViewById<Button>(R.id.login).setOnClickListener() {
            val logintent= Intent(this,Login::class.java)
            startActivity(logintent)
        }
    }
}