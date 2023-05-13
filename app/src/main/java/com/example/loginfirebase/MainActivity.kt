package com.example.loginfirebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var image: ImageView = findViewById(R.id.image)
        var text: TextView = findViewById(R.id.text)
        var editText2: TextView = findViewById(R.id.edittext2)
        auth = FirebaseAuth.getInstance()
        var edittext1: TextView = findViewById(R.id.edittext1)
        var button1: Button = findViewById(R.id.button1)
        button1.setOnClickListener {
            var email = edittext1.text.toString()
            var password = editText2.text.toString()
            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(applicationContext, "All fields are required", Toast.LENGTH_SHORT)
                    .show()

            }
            else {
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(applicationContext, "Login successfull", Toast.LENGTH_SHORT).show()
                            var intent:Intent= Intent(applicationContext,Loggedin::class.java)
                            intent.putExtra("email",email)
                            startActivity(intent)
                            finish()
                        } else {
                            Toast.makeText(applicationContext, "wrong details", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
            }

        }
        var button2:Button=findViewById(R.id.button2)
        button2.setOnClickListener {
            var intent:Intent= Intent(applicationContext,Signup::class.java)
            startActivity(intent)
            finish()
        }

    }
}