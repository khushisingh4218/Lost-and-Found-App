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

val db = Firebase.firestore

var login_users = db.collection("users")
class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        var usern=findViewById<EditText>(R.id.log_email)
        var passw=findViewById<EditText>(R.id.log_pwd)
        var fAuth = Firebase.auth


        findViewById<Button>(R.id.login_btn).setOnClickListener {

            var present = false
            login_users.get()
                .addOnSuccessListener { result ->

                    for (document in result) {


                        val name = document.data.get("username")
                        val password = document.data.get("password")
                        //Log.d(TAG, "${name} => ${usern.getText().toString()}")
                        if (name == usern.getText().toString() && password == passw.getText().toString()) {
                            present = true
                            val verified = fAuth.currentUser?.isEmailVerified
                            //val verified  = user?.isEmailVerified
                            val usertask = fAuth.currentUser?.reload()
                            if(verified==true)
                            {
                                val username_pass = usern.getText().toString()
                                val newIntent = Intent(this, HomeActivity::class.java)
                                newIntent.putExtra("USERNAME", username_pass)
                                newIntent.putExtra("PASSWORD", passw.getText().toString())
                                Log.d(TAG,"username_pass is ${username_pass}")

                                startActivity(newIntent)



                                Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show()
                            }else{
                                Toast.makeText(this, "Verify your email", Toast.LENGTH_SHORT).show()
                            }

                            break

                        }


                    }
                }

        }
    }}