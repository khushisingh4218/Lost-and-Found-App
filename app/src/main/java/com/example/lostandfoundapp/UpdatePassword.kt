package com.example.lostandfoundapp

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText

class UpdatePassword : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_password)

        val newIntent = getIntent()
        val PASSWORD:String? = newIntent.getStringExtra("PASSWORD")
        val USERNAME:String? = newIntent.getStringExtra("USERNAME")

        findViewById<Button>(R.id.submit).setOnClickListener{
            Log.d(TAG, "inside button")
            val prevPass:String? = findViewById<EditText>(R.id.prevPass).getText().toString()
            val newPass:String? = findViewById<EditText>(R.id.newPass).getText().toString()
            val confNewPass:String? = findViewById<EditText>(R.id.confNewPass).getText().toString()

            if(prevPass==PASSWORD && newPass==confNewPass){
                Log.d(TAG, "verified")
                db.collection("users").get().addOnSuccessListener { result->
                    Log.d(TAG, "going to enter loop")
                    for(document in result){

                        Log.d(TAG, "${document.data.get("username").toString()}")

                        if (document.data.get("username").toString()==USERNAME && document.data.get("password").toString()==PASSWORD){

                            db.collection("users").document(document.id).update(mapOf("password" to newPass))
                            Log.d(TAG, "prev is ${prevPass} and new is ${newPass}")
                            finish()
                            break



                        }
                    }
                }
            }
        }


    }
}