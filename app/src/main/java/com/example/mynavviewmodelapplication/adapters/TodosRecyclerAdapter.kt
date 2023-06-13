package com.example.mynavviewmodelapplication.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mynavviewmodelapplication.R
import com.example.mynavviewmodelapplication.models.Todo

class TodosRecyclerAdapter(private val mArrayList: ArrayList<Todo>):
RecyclerView.Adapter<TodosRecyclerAdapter.TodosViewHolder>()
{
    var onItemClick: ((Item: Todo) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodosViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.todo_row_layout, parent, false)
        return TodosViewHolder(view)
    }

    override fun onBindViewHolder(holder: TodosViewHolder, position: Int) {
         val TodosItems = mArrayList[position]
        holder.textViewTitle.text = TodosItems.title
        //holder.textviewCompleted.text = TodosItems.completed.toString()
        holder.completed.isChecked = TodosItems.completed

        holder.itemView.setOnClickListener{
            onItemClick?.invoke(TodosItems)
        }
    }

    override fun getItemCount(): Int {
       return mArrayList.size
    }



    inner class TodosViewHolder(ItemView: View): RecyclerView.ViewHolder(ItemView){
       val textViewTitle: TextView = itemView.findViewById(R.id.TodosTitle)
        val textviewCompleted: TextView = itemView.findViewById(R.id.TodosCompleted)
        val completed: Switch = itemView.findViewById(R.id.TodosCompleted)
    }
}