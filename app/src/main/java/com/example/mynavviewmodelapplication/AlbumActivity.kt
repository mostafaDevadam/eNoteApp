package com.example.mynavviewmodelapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.GridView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley
import com.example.mynavviewmodelapplication.adapters.AlbumsRecyclerAdapter
import com.example.mynavviewmodelapplication.adapters.PhotoGridViewAdapter
import com.example.mynavviewmodelapplication.adapters.PhotosRecyclerAdapter
import com.example.mynavviewmodelapplication.viewmodels.AlbumViewModel
import com.example.mynavviewmodelapplication.viewmodels.AlbumViewModelFactory
import com.example.mynavviewmodelapplication.viewmodels.PhotoViewModel
import com.example.mynavviewmodelapplication.viewmodels.PhotoViewModelFactory

class AlbumActivity : AppCompatActivity() {


    private lateinit var mRequestQueue: RequestQueue
    // album
    private lateinit var albumViewModel: AlbumViewModel
    private lateinit var albumViewModelFactory: AlbumViewModelFactory

    // photos
    private lateinit var photoViewModel: PhotoViewModel
    private lateinit var photoViewModelFactory: PhotoViewModelFactory

    lateinit var gridViewPhotos: GridView

    // ui
    lateinit var textviewAlbumTitle: TextView
    lateinit var recyclerViewPhotos: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album)

        textviewAlbumTitle = findViewById(R.id.textViewAlbumTitleInAlbum)
        gridViewPhotos = findViewById(R.id.grid_view_photos)
        //recyclerViewPhotos = findViewById(R.id.recyclerViewPhotosInAlbum)
       // recyclerViewPhotos.layoutManager = LinearLayoutManager(this)

        mRequestQueue = Volley.newRequestQueue(layoutInflater.context.applicationContext)

        albumViewModelFactory = AlbumViewModelFactory(mRequestQueue)
        albumViewModel = ViewModelProvider(this, albumViewModelFactory)
            .get(AlbumViewModel::class.java)

        photoViewModelFactory = PhotoViewModelFactory(mRequestQueue)
        photoViewModel = ViewModelProvider(this, photoViewModelFactory)
            .get(PhotoViewModel::class.java)


        val albumId = intent.getStringExtra("albumId")
        albumViewModel.getOneAlbumById(albumId.toString())
        albumViewModel.onlineAlbum.observeForever{
            textviewAlbumTitle.text = it.title
        }

        // photos
         photoViewModel.getAllPhotosByAlbumId(albumId.toString())
        photoViewModel.onlinePhotos.observeForever{
            if (it.isEmpty()){

            }else{
                val gridPhotoAdapter = PhotoGridViewAdapter(this, it)

                gridViewPhotos.adapter = gridPhotoAdapter

                gridViewPhotos.setOnItemClickListener { parent, view, position, id ->
                    // intent
                    val intent = Intent(this, PhotoActivity::class.java)
                    intent.putExtra("photoId", "${it[position].id}")
                    startActivity(intent)
                    //
                    Toast.makeText(this, "photo: $it", Toast.LENGTH_LONG).show()
                }

                /*
                val photosAdapter = PhotosRecyclerAdapter(it)
                recyclerViewPhotos.adapter = photosAdapter

                photosAdapter.onItemClick = {
                    // intent
                    val intent = Intent(this, PhotoActivity::class.java)
                    intent.putExtra("photoId", "${it.id}")
                    startActivity(intent)
                    //
                    Toast.makeText(this, "photo: $it", Toast.LENGTH_LONG).show()
                }
                */
            }
        }






    }



}