package com.example.mynavviewmodelapplication.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.example.mynavviewmodelapplication.models.Todo
import org.json.JSONObject


class TodoRepository(var mRequestQueu: RequestQueue) {
var todos = MutableLiveData<ArrayList<Todo>>()
    var todo = MutableLiveData<Todo>()


    fun fetchAllTodos(){
        var url ="https://jsonplaceholder.typicode.com/todos"
        val onlineTodos = ArrayList<Todo>()

        val jsonObjectRequest = JsonArrayRequest(
            Request.Method.GET, url, null,
            Response.Listener {
                for(a in 0 until it.length()){
                    val obj = it.getJSONObject(a)
                    val id = obj.getInt("id")
                    val userId = obj.getInt("userId")
                    val title = obj.getString("title")
                    val completed = obj.getBoolean("completed")

                    var todo = Todo(id, userId, title, completed)
                    onlineTodos.add(todo)
                }
                todos.value = onlineTodos
            }
        ){
            error -> Log.d("response todos error", error.message.toString())
        }

        mRequestQueu.add(jsonObjectRequest)
    }


    fun fetOneTodoById(id: String){
        var url ="https://jsonplaceholder.typicode.com/todos/${id}"

        val jsonObjectRequest = JsonObjectRequest(
        Request.Method.GET, url, null,
        Response.Listener { response ->
            val id = response.getInt("id")
            val userId = response.getInt("userId")
            val title = response.getString("title")
            val completed = response.getBoolean("completed")

            var onlineTodo = Todo(id, userId, title, completed)

            todo.value = onlineTodo

        },
        Response.ErrorListener {
                error -> Log.d("", error.message.toString())
        }
        )




        mRequestQueu.add(jsonObjectRequest)

    }

    fun fetchnodehttpLocalserver(){
        val url ="http://192.168.1.13:3000"
        val jsonObjRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            Response.Listener { response ->

               println("http response : $response")
                Log.d("http**", response.toString())

            },
            Response.ErrorListener {
                    error -> Log.d("", error.message.toString())
            }
        )
        mRequestQueu.add(jsonObjRequest)
    }

    fun fetchnodeExpressLocalserver(){
        val url ="http://192.168.1.13:4000/api"
        val url2 ="http://localhost:4000/api"
        val jsonObjRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            Response.Listener { response ->

                println("http response : $response")
                Log.d("express**", response.toString())

            },
            Response.ErrorListener {
                    error -> Log.d("", error.message.toString())
            }
        )
        mRequestQueu.add(jsonObjRequest)
    }


    fun PostExpressLocalserver(data: Todo){
        val url ="http://192.168.1.13:4000/api"

        val td = JSONObject()
        td.put("title",data!!.title)
        td.put("id", data!!.id)
        td.put("completed", data.completed)

        val jsonObj = JSONObject()
        jsonObj.put("data", td)


        val jsonObjRequest = JsonObjectRequest(
            Request.Method.POST, url,
            jsonObj,
            Response.Listener { response ->

                println("http response : $response")
                Log.d("express**", response.toString())

            },
            Response.ErrorListener {
                    error -> Log.d("", error.message.toString())
            }
        )


        mRequestQueu.add(jsonObjRequest)
    }

    fun fetchnodeLocalserver(){

        val url ="http://127.0.0.1:5000/api"
        val jsonObjRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            Response.Listener { response ->

                println("http response : $response")
                Log.d("local**", response.toString())

            },
            Response.ErrorListener {
                    error -> Log.d("", error.message.toString())
            }
        )
        mRequestQueu.add(jsonObjRequest)
    }




}