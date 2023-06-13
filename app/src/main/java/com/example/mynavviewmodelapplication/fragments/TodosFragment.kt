package com.example.mynavviewmodelapplication.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley
import com.example.mynavviewmodelapplication.R
import com.example.mynavviewmodelapplication.TodoActivity
import com.example.mynavviewmodelapplication.adapters.TodosRecyclerAdapter
import com.example.mynavviewmodelapplication.databinding.FragmentTodosBinding
import com.example.mynavviewmodelapplication.viewmodels.CommentViewModelFactory
import com.example.mynavviewmodelapplication.viewmodels.TodoViewModel
import com.example.mynavviewmodelapplication.viewmodels.TodoViewModelFactory


class TodosFragment : Fragment() {


    private lateinit var todoViewModel: TodoViewModel
    private lateinit var todoViewModelFactory: TodoViewModelFactory
    private lateinit var mRequestQueue: RequestQueue


    lateinit var recyclerViewTodos: RecyclerView

    private lateinit var binding: FragmentTodosBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mRequestQueue = Volley.newRequestQueue(layoutInflater.context.applicationContext)
        todoViewModelFactory = TodoViewModelFactory(mRequestQueue)
        todoViewModel = ViewModelProvider(this, todoViewModelFactory)
            .get(TodoViewModel::class.java)

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentTodosBinding.bind(view)
        //binding.todosviewmodel = todoViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_todos, container, false)

        recyclerViewTodos = view.findViewById(R.id.recyclerViewTodos)
        recyclerViewTodos.layoutManager = LinearLayoutManager(view.context)

        todoViewModel.onlineTodos.observeForever{
            if(it.isEmpty()){
                Log.d("Online Todos", "No Online Todos")
            }else{
                Log.d("Online Todos Data", it.toString())

                val todosRecyclerAdapter = TodosRecyclerAdapter(it)
                recyclerViewTodos.adapter = todosRecyclerAdapter

                todosRecyclerAdapter.onItemClick = {
                    // intent
                    val intent = Intent(view.context, TodoActivity::class.java)
                    intent.putExtra("todoId", "${it.id}")
                    startActivity(intent)
                }
            }
        }




        return view
    }

}