package com.example.loginfirebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.GridView
import android.widget.ListView
import android.widget.Toast

class food : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food)
        var gridview:GridView=findViewById(R.id.grid)
        var arr1= intArrayOf(R.drawable.pizza,R.drawable.pancake,R.drawable.pastry,R.drawable.kbaab
        ,R.drawable.toast,R.drawable.egg,R.drawable.chowmin)

        var gridviewAdapter:GridviewAdapter= GridviewAdapter(applicationContext,arr1)
        gridview.adapter=gridviewAdapter

        gridview.setOnItemClickListener {
                adapterView, view, i, l ->
            var intent:Intent= Intent(applicationContext,Detail::class.java)
            intent.putExtra("image", arr1[i])
            startActivity(intent)
        }

    }

   }