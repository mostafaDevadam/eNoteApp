package com.example.mynavviewmodelapplication.viewmodels

import androidx.databinding.Observable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.RequestQueue
import com.example.mynavviewmodelapplication.models.Photo
import com.example.mynavviewmodelapplication.repository.PhotoRepository

class PhotoViewModel(var mRequestQueue: RequestQueue): ViewModel(), Observable {
    var onlinePhotos = MutableLiveData<ArrayList<Photo>>()
    var onlinePhoto = MutableLiveData<Photo>()
    var photoRepo = PhotoRepository(mRequestQueue)

    init{}

    fun getAllPhotosByAlbumId(albumId: String){
        photoRepo.fetchAllPhotosByAlbumId(albumId)
        onlinePhotos = photoRepo.photos
    }

    fun getOnePhotoById(id: String){
        photoRepo.fetchOnePhotoById(id)
        onlinePhoto = photoRepo.photo
    }




    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }
}