package com.example.mynavviewmodelapplication.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.mynavviewmodelapplication.R
import com.example.mynavviewmodelapplication.models.Album

class AlbumsGridViewAdapter(context: Context, val arrayItems: ArrayList<Album>) : BaseAdapter() {

    var layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int {
        return arrayItems.size
    }

    override fun getItem(position: Int): Any {
        return arrayItems[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view = convertView
        if(view == null){
            view = layoutInflater.inflate(R.layout.album_row_grid, parent, false)

        }
        var albumImage = view!!.findViewById<ImageView>(R.id.item_grid_album_image)
        var albumTitle = view!!.findViewById<TextView>(R.id.item_grid_album_title)

        albumTitle.text = arrayItems[position].title
        albumImage.setImageResource(R.drawable.natur1)

        albumTitle.maxLines = 1






        return view!!

    }
}