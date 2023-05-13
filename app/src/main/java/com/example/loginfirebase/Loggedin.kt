package com.example.loginfirebase

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.firebase.firestore.FirebaseFirestore

class Loggedin : AppCompatActivity() {
    private lateinit var db:FirebaseFirestore
    lateinit var text1:TextView
    lateinit var text2:TextView
    lateinit var text3:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loggedin)
        var button4:Button=findViewById(R.id.button4)
        text1=findViewById(R.id.text1)
        text2=findViewById(R.id.text2)
        text3=findViewById(R.id.text3)
        var button5:Button=findViewById(R.id.button5)
        button5.setOnClickListener {
            var intent:Intent= Intent(applicationContext,food::class.java)
            startActivity(intent)
        }

        val sharedPref=this.getPreferences(Context.MODE_PRIVATE)?:return
        val islogin=sharedPref.getString("email","1")
        button4.setOnClickListener {
            sharedPref.edit().remove("email").apply()
            var intent=Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        if(islogin=="1")
        {
            var email=intent.getStringExtra("email")
            if(email!=null)
            {
                with(sharedPref.edit())
                {
                    putString("email",email)
                    apply()
                }
            }
            else
            {
                var intent=Intent(this,MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
        else
        {
            settext(islogin)
        }
    }

    private fun settext(email: String?) {
        db= FirebaseFirestore.getInstance()
        if (email != null) {
            db.collection("USERS").document(email).get()
                .addOnSuccessListener {
                    tasks->
                    text3.text = tasks.get("email").toString()
                    text2.text = tasks.get("mobile").toString()
                    text1.text = tasks.get("name").toString()
                }
        }
    }
}