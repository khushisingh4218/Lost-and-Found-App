package com.example.lostandfoundapp

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText

class Claim_Found : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_claim_found)

        val myIntent = getIntent()
        val bundle:Bundle? = myIntent.getBundleExtra("bundle")
        val USERNAME:String? = bundle?.getString("USERNAME")
        val ITEM:String? = bundle?.getString("ITEM")
        val DATE:String? = bundle?.getString("DATE")

        Log.d(ContentValues.TAG,"CLAIM USERNAME is ${USERNAME}")


        findViewById<Button>(R.id.claim_submit_btn).setOnClickListener(){
            Log.d(TAG, "button pressed")


            val newIntent = Intent(this,Claim_part2 ::class.java)
            newIntent.putExtra("USERNAME", USERNAME)
            newIntent.putExtra("name", findViewById<EditText>(R.id.claim_name).getText().toString())
            newIntent.putExtra("email", findViewById<EditText>(R.id.claim_email).getText().toString())
            newIntent.putExtra("contact", findViewById<EditText>(R.id.claim_contact).getText().toString())
            startActivity(newIntent)


            db.collection("lost").get().addOnSuccessListener { result->

                for(document in result){
                    Log.d(TAG,"document is ${document.data.get("Item").toString()} and ${document.data.get("Date").toString()}")
                    if (document.data.get("Item").toString()==ITEM && document.data.get("Date").toString()==DATE && document.data.get("Settled").toString()=="False" ){
                        Log.d(TAG, "value has been updates")

                        db.collection("lost").document(document.id).update(mapOf("Settled" to "True")).addOnSuccessListener { result->
                            Log.d(TAG,"achieved")
                        }
                        Log.d(TAG, "settled is ${document.data.get("Settled").toString()} ")

                        break
                    }
                }
            }
            finish()


        }
    }
}