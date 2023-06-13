package com.example.mynavviewmodelapplication.repository
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.android.volley.RequestQueue
import com.example.mynavviewmodelapplication.models.Post
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import java.util.Objects

class PostRepository(var mRequestQueue: RequestQueue) {
    var posts = MutableLiveData<ArrayList<Post>>()
    var post = MutableLiveData<Post>()
    var posts_ = MutableLiveData<ArrayList<Post>>()

    fun fetchAllPosts(){
        val url = "https://jsonplaceholder.typicode.com/posts"
        var onlinePosts = ArrayList<Post>()

        val jsonObjectRequest = JsonArrayRequest(
            Request.Method.GET, url, null,
            Response.Listener {

                    for (a in 0 until it.length()) {
                        val obj = it.getJSONObject(a)
                        val userId = obj.getInt("userId")
                        val id = obj.getInt("id")
                        val title = obj.getString("title")
                        val body = obj.getString("body")

                        var post = Post(userId, id, title, body)
                        onlinePosts.add(post)


                    }

                posts.value = onlinePosts



            }
        ) {
            error -> Log.d("error", error.message.toString())
        }

        mRequestQueue.add(jsonObjectRequest)

    }

    fun fetOnePostById(id: String) {
        val url = "https://jsonplaceholder.typicode.com/posts/$id"


        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            Response.Listener { response ->
               // textView.text = "Response: %s".format(response.toString())
                val userId = response.getInt("userId")
                val id = response.getInt("id")
                val title = response.getString("title")
                val body = response.getString("body")

                var onlinePost = Post(userId, id, title, body)


                post.value = onlinePost

                Log.d("response post-", "$post")

            },
            Response.ErrorListener { error ->
                Log.d("error post", error.message.toString())
            }
        )

        mRequestQueue?.add(jsonObjectRequest)

    }

    fun fetchPost(id: String){
        val url = "https://jsonplaceholder.typicode.com/posts/"+id.toInt()
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            Response.Listener {
                println("jsonar $it")

                    val userId = it.getInt("userId")
                    val id = it.getInt("id")
                    val title = it.getString("title")
                    val body = it.getString("body")

                    var p = Post(userId, id, title, body)
                    post.value = p
            }
        ) {
                error -> Log.d("error post_", error.message.toString())
        }

        println("jsonar post  $post")

        mRequestQueue.add(jsonObjectRequest)


    }
}