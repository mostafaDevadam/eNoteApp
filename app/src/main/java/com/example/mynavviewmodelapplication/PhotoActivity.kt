package com.example.mynavviewmodelapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley
import com.example.mynavviewmodelapplication.viewmodels.AlbumViewModel
import com.example.mynavviewmodelapplication.viewmodels.AlbumViewModelFactory
import com.example.mynavviewmodelapplication.viewmodels.PhotoViewModel
import com.example.mynavviewmodelapplication.viewmodels.PhotoViewModelFactory
import com.squareup.picasso.Picasso

class PhotoActivity : AppCompatActivity() {

    lateinit var textViewPhotoTitle: TextView
    private lateinit var imageViewPhotoUrl: ImageView


    private lateinit var mRequestQueue: RequestQueue


    // photos
    private lateinit var photoViewModel: PhotoViewModel
    private lateinit var photoViewModelFactory: PhotoViewModelFactory


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo)

        textViewPhotoTitle = findViewById(R.id.textViewPhotoTitleInPhoto)
        imageViewPhotoUrl = findViewById(R.id.imageViewPhotoUrlInPhoto)

        mRequestQueue = Volley.newRequestQueue(layoutInflater.context.applicationContext)
        photoViewModelFactory = PhotoViewModelFactory(mRequestQueue)
        photoViewModel = ViewModelProvider(this, photoViewModelFactory)
            .get(PhotoViewModel::class.java)

        val photoId = intent.getStringExtra("photoId")

        photoViewModel.getOnePhotoById(photoId.toString())

        photoViewModel.onlinePhoto.observeForever{
            textViewPhotoTitle.text = it.title
            Picasso.get().load(it.thumbnailUrl).into(imageViewPhotoUrl)
        }


    }
}