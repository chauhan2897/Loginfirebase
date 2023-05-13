package com.example.loginfirebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class Signup : AppCompatActivity() {
    private lateinit var auth:FirebaseAuth
    private lateinit var db:FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        var image2:ImageView=findViewById(R.id.image2)
        var edittext3:EditText=findViewById(R.id.edittext3)
        var editext4:EditText=findViewById(R.id.edittext4)
        var edittext5:EditText=findViewById(R.id.edittext5)
        var edittext6:EditText=findViewById(R.id.edittext6)
        var button3:Button=findViewById(R.id.button3)
        auth= FirebaseAuth.getInstance()
        db= FirebaseFirestore.getInstance()
        button3.setOnClickListener {
            var name=edittext3.text.toString()
            var mobile=editext4.text.toString()
            var email = edittext5.text.toString()
            var password = edittext6.text.toString()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(applicationContext, "All fields are required", Toast.LENGTH_SHORT)
                    .show()

            }
            else {
                val user= hashMapOf(
                    "name" to name,
                    "mobile" to mobile,
                    "email" to email
                )
                val Users=db.collection("USERS")
                val query=Users.whereEqualTo("email",email).get()
                    .addOnSuccessListener {
                        task->
                        if(task.isEmpty)
                        {
                            auth.createUserWithEmailAndPassword(email, password)
                                .addOnCompleteListener(this) {
                                        task ->
                                    if (task.isSuccessful)
                                    {
                                        Users.document(email).set(user)
                                        Toast.makeText(this,"on create successfull",Toast.LENGTH_SHORT).show()
                                        val intent:Intent= Intent(this,MainActivity::class.java)
                                        intent.putExtra("email",email)
                                        startActivity(intent)
                                        finish()
                                    } else
                                    {
                                        Toast.makeText(applicationContext, "Authentication failed", Toast.LENGTH_SHORT)
                                            .show()
                                    }
                                }
                        }else
                        {
                            Toast.makeText(applicationContext, "User Already Registered", Toast.LENGTH_SHORT).show()
                            var intent:Intent= Intent(applicationContext,MainActivity::class.java)
                            startActivity(intent)
                        }
                    }

            }
        }
    }
}