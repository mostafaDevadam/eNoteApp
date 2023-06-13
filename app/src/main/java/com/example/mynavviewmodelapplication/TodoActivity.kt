package com.example.mynavviewmodelapplication

import android.icu.text.CaseMap.Title
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley
import com.example.mynavviewmodelapplication.viewmodels.TodoViewModel
import com.example.mynavviewmodelapplication.viewmodels.TodoViewModelFactory

class TodoActivity : AppCompatActivity() {

    private lateinit var todoViewModel: TodoViewModel
    private lateinit var todoViewModelFactory: TodoViewModelFactory
    private lateinit var mRequestQueue: RequestQueue

    lateinit var textViewTodoTitle: TextView
    lateinit var textViewTodoCompleted: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo)

        textViewTodoTitle = findViewById(R.id.textViewTodoTitleInTodo)
      //  textViewTodoCompleted = findViewById(R.id.textViewCompletedInTodo)

        mRequestQueue = Volley.newRequestQueue(layoutInflater.context.applicationContext)
        todoViewModelFactory = TodoViewModelFactory(mRequestQueue)
        todoViewModel = ViewModelProvider(this, todoViewModelFactory)
            .get(TodoViewModel::class.java)


        val todoId = intent.getStringExtra("todoId")
        println("todoId in Todo $todoId")


        todoViewModel.getOneTodoById(todoId.toString())

        todoViewModel.onlineTodo.observeForever{
            textViewTodoTitle.text = it.title
           // textViewTodoCompleted.text = it.completed.toString()
        }


    }
}