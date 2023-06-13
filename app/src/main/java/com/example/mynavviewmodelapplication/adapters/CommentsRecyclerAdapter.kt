package com.example.mynavviewmodelapplication.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mynavviewmodelapplication.R
import com.example.mynavviewmodelapplication.models.Comment

class CommentsRecyclerAdapter(private val mArrayList: ArrayList<Comment>):
    RecyclerView.Adapter<CommentsRecyclerAdapter.CommentsViewHolder>()
{
   var onItemclick: ((Item: Comment) -> Unit)? = null


    @SuppressLint("SuspiciousIndentation")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentsViewHolder {
     val view = LayoutInflater.from(parent.context)
         .inflate(R.layout.comment_row_layout, parent, false)
        return CommentsViewHolder(view)
    }

    override fun onBindViewHolder(holder: CommentsViewHolder, position: Int) {
        val commentItems = mArrayList[position]
       // holder.textViewCommentName.text = commentItems.name
        holder.textViewCommentBody.text = commentItems.body

        holder.itemView.setOnClickListener{
            onItemclick?.invoke(commentItems)
        }
    }

    override fun getItemCount(): Int {
       return mArrayList.size
    }



    inner class CommentsViewHolder(ItemView: View): RecyclerView.ViewHolder(ItemView) {
       // val textViewCommentTitle: TextView = itemView.findViewById(R.id.commentTitle)
       // val textViewCommentName: TextView = itemView.findViewById(R.id.commentName)
        val textViewCommentBody: TextView = itemView.findViewById(R.id.commentBody)

    }

}