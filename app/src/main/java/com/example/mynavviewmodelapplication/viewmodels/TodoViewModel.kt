package com.example.mynavviewmodelapplication.viewmodels

import androidx.databinding.Observable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.RequestQueue
import com.example.mynavviewmodelapplication.models.Todo
import com.example.mynavviewmodelapplication.repository.TodoRepository

class TodoViewModel(var mRequestQueue: RequestQueue): ViewModel(), Observable {

    var onlineTodos = MutableLiveData<ArrayList<Todo>>()
    var onlineTodo = MutableLiveData<Todo>()
    var todoRepo = TodoRepository(mRequestQueue)

    init {
        todoRepo.fetchAllTodos()
        onlineTodos = todoRepo.todos
        todoRepo.fetchnodehttpLocalserver()
        todoRepo.fetchnodeExpressLocalserver()
       // todoRepo.fetchnodeLocalserver()
        val data = Todo(1,1,"mytitle",false)
        todoRepo.PostExpressLocalserver(data)
    }

    fun getOneTodoById(id: String){
        todoRepo.fetOneTodoById(id)
        onlineTodo = todoRepo.todo
    }






    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }
}