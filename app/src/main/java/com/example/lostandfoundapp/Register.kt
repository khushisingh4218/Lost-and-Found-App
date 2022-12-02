package com.example.lostandfoundapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

val db0 = Firebase.firestore

var reg_users = db0.collection("users")

class Register : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        var username=findViewById<EditText>(R.id.reg_email)
        var password=findViewById<EditText>(R.id.reg_pwd)
        var confirm_password=findViewById<EditText>(R.id.reg_cnfpwd)
        var fAuth= Firebase.auth
        findViewById<Button>(R.id.reg_btn).setOnClickListener {
            validateEmpty()
        }
    }
    private fun validateEmpty(){
        var icon = AppCompatResources.getDrawable(this,R.drawable.error)
        icon?.setBounds(0,0,100,100)
        var username=findViewById<EditText>(R.id.reg_email)
        var password=findViewById<EditText>(R.id.reg_pwd)
        var confirm_password=findViewById<EditText>(R.id.reg_cnfpwd)
        var fAuth= Firebase.auth
        when {
            TextUtils.isEmpty(username.text.toString().trim()) -> {
                username.setError("Please enter username", icon)
            }
            TextUtils.isEmpty(password.text.toString().trim()) -> {
                password.setError("Please enter password", icon)
            }
            TextUtils.isEmpty(confirm_password.text.toString().trim()) -> {
                confirm_password.setError("Please re-enter password", icon)
            }

        username.text.toString().isNotEmpty() &&
                password.text.toString().isNotEmpty() &&
                confirm_password.text.toString().isNotEmpty()-> {

            if (username.text.toString().matches(Regex("[a-zA-Z0-9._-]+@iitp.ac.in"))){
                if (password.text.toString().length>=5){
                    if(password.text.toString()==confirm_password.text.toString()){
                        fireBaseSignUp()
                        // Toast.makeText(context, "Registration Successful",Toast.LENGTH_SHORT).show()
                    }else{
                        confirm_password.setError("Password Mismatched",icon)
                    }
                }else{
                    password.setError("Password is too short",icon)
                }
            }else{
                username.setError("Please enter webmail id",icon)
            }}
        }

}
    private fun fireBaseSignUp(){
        var fAuth=Firebase.auth
        var username=findViewById<EditText>(R.id.reg_email)
        var password=findViewById<EditText>(R.id.reg_pwd)
        var confirm_password=findViewById<EditText>(R.id.reg_cnfpwd)
        fAuth.createUserWithEmailAndPassword(username.text.toString(),password.text.toString()).addOnCompleteListener(){
                task->
            if(task.isSuccessful){

                fAuth.currentUser?.sendEmailVerification()?.addOnCompleteListener {
                    Toast.makeText(this, "Registration Successful", Toast.LENGTH_SHORT).show()
                }




                val users = hashMapOf("username" to username.text.toString(), "password" to password.text.toString())
                reg_users.add(users)
                val verified = fAuth.currentUser?.isEmailVerified
                if (verified==true){
                    val newIntent = Intent(this, HomeActivity::class.java)
                    startActivity(newIntent)

                }else{
                    Toast.makeText(this, "Verify your mail", Toast.LENGTH_SHORT).show()
                }




//                val users = hashMapOf("username" to username.text.toString(), "password" to password.text.toString(), "authentication" to true)
//                reg_users.add(users)


            }else{
                Toast.makeText(this,task.exception?.message, Toast.LENGTH_SHORT).show()
            }
        }

    }
}