package com.example.mynavviewmodelapplication.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.example.mynavviewmodelapplication.models.Album
import com.example.mynavviewmodelapplication.models.Photo
import org.json.JSONObject

class PhotoRepository(var mRequestQueue: RequestQueue) {
    var photos = MutableLiveData<ArrayList<Photo>>()
    var photo = MutableLiveData<Photo>()



    fun fetchAllPhotosByAlbumId(albumId: String){
        val url = "https://jsonplaceholder.typicode.com/photos/?albumId=$albumId"
        var onlinePhotos = ArrayList<Photo>()
        val jsonObjectRequest = JsonArrayRequest(
            Request.Method.GET, url, null,
            Response.Listener {
                for (a in 0 until it.length()){
                    val obj = it.getJSONObject(a)
                    val id = obj.getInt("id")
                    val albumId = obj.getInt("albumId")
                    val title = obj.getString("title")
                    val thumbnailUrl = obj.getString("thumbnailUrl")


                    var photo = Photo(id, albumId, title, thumbnailUrl)
                    onlinePhotos.add(photo)
                }
                photos.value = onlinePhotos

            }
        ){
            error -> Log.d("error", error.message.toString())
        }

        mRequestQueue.add(jsonObjectRequest)


    }

    fun fetchOnePhotoById(id: String){
        val url = "https://jsonplaceholder.typicode.com/photos/$id"
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            Response.Listener {
                val id = it.getInt("id")
                val albumId = it.getInt("albumId")
                val title = it.getString("title")
                val thumbnailUrl = it.getString("thumbnailUrl")

                var onlinePhoto = Photo(id, albumId, title, thumbnailUrl)
                photo.value = onlinePhoto


            }
        ,
            Response.ErrorListener { error ->
                Log.d("response photo error", error.message.toString())
            }
        )

        mRequestQueue.add(jsonObjectRequest)

    }
}