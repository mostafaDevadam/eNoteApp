package com.example.mynavviewmodelapplication.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.example.mynavviewmodelapplication.models.User

class UserRepository(var mRequestQueue: RequestQueue) {
    var url = "https://jsonplaceholder.typicode.com/users/"

    var user = MutableLiveData<User>()

    fun fetchOneUserById(id: Int){

        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url+id, null,
            Response.Listener{ response ->
                var id= response.getInt("id")
                var name = response.getString("name")
                var username = response.getString("username")
                var email = response.getString("email")
                var phone = response.getString("phone")
                var website = response.getString("website")


                val onlineUser = User(id, name, username, email, phone, website)
                user.value = onlineUser //User(id, name, username, email, phone.toInt(), website)

                Log.d("response user:", "$onlineUser")


            },
            Response.ErrorListener { error ->
                Log.d("error user", "${error.message}")
            }
        )

        mRequestQueue?.add(jsonObjectRequest)

    }
}