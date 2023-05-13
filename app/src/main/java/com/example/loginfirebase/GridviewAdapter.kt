package com.example.loginfirebase

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView

class GridviewAdapter(context: Context, var data: IntArray) : BaseAdapter() {
    var inflater: LayoutInflater = LayoutInflater.from(context)
    override fun getCount(): Int {
        return data.size
    }

    override fun getItem(p0: Int): Any? {
        return null
    }

    override fun getItemId(p0: Int): Long {
        return 0
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        var p1=inflater.inflate(R.layout.resource_item,null)
        var image3: ImageView =p1.findViewById(R.id.image3)
        image3.setImageResource(data[p0])
        return p1
    }
}