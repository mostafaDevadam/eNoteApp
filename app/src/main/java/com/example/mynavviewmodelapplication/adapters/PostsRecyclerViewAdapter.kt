package com.example.mynavviewmodelapplication.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mynavviewmodelapplication.R
import com.example.mynavviewmodelapplication.models.Post
import com.example.mynavviewmodelapplication.viewmodels.UserViewModel

class PostsRecyclerViewAdapter(private val mArrList: ArrayList<Post>, private val userViewModel: UserViewModel):
    RecyclerView.Adapter<PostsRecyclerViewAdapter.PostsViewHolder>() {
     var onItemClick: ((Item: Post) -> Unit)? = null


    override fun getItemCount(): Int {
        return mArrList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.post_row_layout, parent, false)
        return PostsViewHolder(view)
    }



    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) {
        val items = mArrList[position]
       // holder.textViewTitle.text = Items.title
       // holder.textViewTitle.maxLines = 1

        userViewModel.getOneUserId(items.id)

        userViewModel.onlineUser.observeForever{
            holder.textViewUserNameInFeeds.text = it.name
        }



        holder.itemView.setOnClickListener{
            onItemClick?.invoke(items)
        }

    }



    inner class PostsViewHolder(ItemView: View): RecyclerView.ViewHolder(ItemView){
        val textViewTitle: TextView = itemView.findViewById(R.id.postTitle)
        var textViewUserNameInFeeds: TextView = itemView.findViewById(R.id.textViewUserNameInFeeds)


        /*init {
            itemView.setOnClickListener{
                onItemClick?.invoke(mArrList[position])
            }
        }*/




    }
}