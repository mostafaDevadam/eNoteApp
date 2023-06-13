package com.example.mynavviewmodelapplication.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.example.mynavviewmodelapplication.models.Album
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest

class AlbumRepository(var mRequestQueue: RequestQueue) {
    var albums = MutableLiveData<ArrayList<Album>>()
    var album = MutableLiveData<Album>()


    fun fetchAllAlbums(){
        var url ="https://jsonplaceholder.typicode.com/albums"

        val onlineAlbums = ArrayList<Album>()

        val jsonObjectRequest = JsonArrayRequest(
            Request.Method.GET, url, null,
            Response.Listener {
                for (a in 0 until it.length()){
                    val obj = it.getJSONObject(a)
                    val id = obj.getInt("id")
                    var userId = obj.getInt("userId")
                    var title = obj.getString("title")

                    var album = Album(id, userId, title)
                    onlineAlbums.add(album)

                }

                albums.value = onlineAlbums
            }

        ){
            error -> Log.d("Response Albums error" , error.message.toString())
        }

        mRequestQueue.add(jsonObjectRequest)


    }

    fun fetchOneAlbumById(id: String){
        var url = "https://jsonplaceholder.typicode.com/albums/$id"
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            Response.Listener {
                val id = it.getInt("id")
                val userId = it.getInt("userId")
                val title = it.getString("title")

                var onlineAlbum = Album(id, userId, title)

                album.value = onlineAlbum
            },
            Response.ErrorListener {
                error -> Log.d("Response Album error", error.message.toString())
            }
        )

        mRequestQueue.add(jsonObjectRequest)


    }


}