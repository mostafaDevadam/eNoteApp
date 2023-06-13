package com.example.mynavviewmodelapplication.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mynavviewmodelapplication.R
import com.example.mynavviewmodelapplication.models.Album

class AlbumsRecyclerAdapter(private val mArrayList: ArrayList<Album>):
RecyclerView.Adapter<AlbumsRecyclerAdapter.AlbumsViewHolder>()
{
    var onItemClick: ((Item: Album) -> Unit)? = null

    override fun getItemCount(): Int {
          return mArrayList.size
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumsViewHolder {
       val view = LayoutInflater.from(parent.context)
           .inflate(R.layout.album_row_layout, parent, false)
        return AlbumsViewHolder(view)
    }



    override fun onBindViewHolder(holder: AlbumsViewHolder, position: Int) {
        val AlbumsItems = mArrayList[position]
        holder.textViewTitle.text = AlbumsItems.title

        holder.itemView.setOnClickListener{
            onItemClick?.invoke(AlbumsItems)
        }
    }

    inner class AlbumsViewHolder(ItemView: View): RecyclerView.ViewHolder(ItemView){
        val textViewTitle: TextView = itemView.findViewById(R.id.AlbumsTitle)


    }
}