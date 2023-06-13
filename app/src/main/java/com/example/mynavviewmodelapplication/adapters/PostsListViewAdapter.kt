package com.example.mynavviewmodelapplication.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.mynavviewmodelapplication.R
import com.example.mynavviewmodelapplication.models.Post
import org.w3c.dom.Text

class PostsListViewAdapter(
    private val context: Context,
    private val arrayList: ArrayList<Post>,
): BaseAdapter() {

    private lateinit var postTitle: TextView
    private lateinit var postBody: TextView

    override fun getCount(): Int {
       return  arrayList.size
    }

    override fun getItem(position: Int): Any {
       return position
    }

    override fun getItemId(position: Int): Long {
       return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
     var view = convertView
        view = LayoutInflater.from(context).inflate(R.layout.post_row_layout, parent, false)

     //   postTitle = view.findViewById(R.id.postTitle)
       // postBody = view.findViewById(R.id.postBody)

       // postTitle.text = arrayList[position].title
       // postBody.text = arrayList[position].body


        return view
    }
}