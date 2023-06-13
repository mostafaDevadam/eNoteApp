package com.example.mynavviewmodelapplication.repository
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.android.volley.RequestQueue
import com.android.volley.Request
import com.android.volley.Response

import com.android.volley.toolbox.JsonArrayRequest

import com.example.mynavviewmodelapplication.models.Comment

class CommentRepository(var mRequestQueue: RequestQueue) {
    var comments = MutableLiveData<ArrayList<Comment>>()

    fun fetchAllCommentsByPostId(postId: String){
        val url = "https://jsonplaceholder.typicode.com/comments?postId=$postId"
        var onlineComments = ArrayList<Comment>()

        val jsonObjectRequest = JsonArrayRequest(
            Request.Method.GET, url, null,
           Response.Listener {
               for(a in 0 until it.length()){
                   val obj = it.getJSONObject(a)
                   val id = obj.getInt("id")
                   val postId = obj.getInt("postId")
                   val name = obj.getString("name")
                   val email = obj.getString("email")
                   val body = obj.getString("body")

                   var comment = Comment(id, postId,name, email, body)
                   onlineComments.add(comment)
               }
               comments.value = onlineComments
           }

        ){
            error -> Log.d("comments data error", error.message.toString())
        }

        mRequestQueue.add(jsonObjectRequest)

    }
}