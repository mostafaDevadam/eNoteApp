package com.example.mynavviewmodelapplication.adapters

import android.content.Context
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.mynavviewmodelapplication.R
import com.example.mynavviewmodelapplication.models.Photo
import com.squareup.picasso.Picasso


class PhotoGridViewAdapter(context: Context,val arrayItems: ArrayList<Photo>): BaseAdapter() {

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
            view = layoutInflater.inflate(R.layout.photo_row_grid_layout, parent, false)
        }
        val photoTitle: TextView = view!!.findViewById(R.id.item_grid_photo_title)
        val photoUrl: ImageView = view!!.findViewById(R.id.item_grid_photo_image)

        photoTitle.text = arrayItems[position].title
        //Picasso.get().load(arrayItems[position].thumbnailUrl).into(photoUrl)
        photoUrl.setImageResource(R.drawable.natur2)

        photoTitle.maxLines = 1




        return view!!

    }
}