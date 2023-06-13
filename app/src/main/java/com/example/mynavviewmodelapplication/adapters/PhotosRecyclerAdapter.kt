package com.example.mynavviewmodelapplication.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mynavviewmodelapplication.R
import com.example.mynavviewmodelapplication.models.Photo
import com.squareup.picasso.Picasso

class PhotosRecyclerAdapter(private val mArrayList: ArrayList<Photo>):
RecyclerView.Adapter<PhotosRecyclerAdapter.PhotosViewHolder>()
{
     var onItemClick: ((Item: Photo) -> Unit)? = null

    override fun getItemCount(): Int {
        return mArrayList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotosViewHolder {
          val view = LayoutInflater.from(parent.context)
              .inflate(R.layout.photo_row_layout, parent, false)
        return PhotosViewHolder(view)
    }



    override fun onBindViewHolder(holder: PhotosViewHolder, position: Int) {
      val photosItems = mArrayList[position]
       holder.textViewPhotoTitle.text = photosItems.title
       // holder.imageViewPhotoUrl.setImageResource(photosItems.thumbnailUrl)
        Picasso.get().load(photosItems.thumbnailUrl).into(holder.imageViewPhotoUrl)
        holder.itemView.setOnClickListener {
            onItemClick?.invoke(photosItems)
        }

    }







    inner class PhotosViewHolder(ItemView: View): RecyclerView.ViewHolder(ItemView){
        val textViewPhotoTitle: TextView = itemView.findViewById(R.id.PhotosTitle)
        val imageViewPhotoUrl: ImageView = itemView.findViewById(R.id.imageViewPhotoUrl)
    }


}