package com.example.lostandfoundapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        var newintent = getIntent()
        val USERNAME = newintent.getStringExtra("USERNAME").toString()

        findViewById<Button>(R.id.found).setOnClickListener {
            val foundIntent= Intent(this,Found::class.java)
            foundIntent.putExtra("USERNAME", USERNAME)
            startActivity(foundIntent)
        }
        findViewById<Button>(R.id.lost).setOnClickListener {
            val lostIntent= Intent(this,Lost::class.java)
            lostIntent.putExtra("USERNAME", USERNAME)
            startActivity(lostIntent)
        }
        findViewById<Button>(R.id.found_list).setOnClickListener {
            val foundList= Intent(this,Found_list::class.java)
            startActivity(foundList)
        }
        findViewById<Button>(R.id.lost_list).setOnClickListener {
            val lostList= Intent(this,Lost_list::class.java)
            startActivity(lostList)
        }
        findViewById<Button>(R.id.my_post).setOnClickListener {
            val feed= Intent(this,Feed::class.java)
            feed.putExtra("USERNAME",USERNAME)
            startActivity(feed)
        }
        findViewById<Button>(R.id.update_pwd).setOnClickListener {
            val pwdUpdate= Intent(this,UpdatePassword::class.java)
            startActivity(pwdUpdate)
        }
    }
}