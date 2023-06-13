package com.example.mynavviewmodelapplication.viewmodels

import androidx.databinding.Observable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.RequestQueue
import com.example.mynavviewmodelapplication.models.Album
import com.example.mynavviewmodelapplication.repository.AlbumRepository

class AlbumViewModel(var mRequestQueue: RequestQueue) : ViewModel(), Observable {

    var onlineAlbums = MutableLiveData<ArrayList<Album>>()
    var onlineAlbum = MutableLiveData<Album>()
    var albumRepo = AlbumRepository(mRequestQueue)

    init {
        albumRepo.fetchAllAlbums()
        onlineAlbums = albumRepo.albums
    }

    fun getOneAlbumById(id: String){
        albumRepo.fetchOneAlbumById(id)
        onlineAlbum = albumRepo.album
    }









    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

}