package com.example.mynavviewmodelapplication.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.volley.RequestQueue

class AlbumViewModelFactory(var mRequestQueue: RequestQueue): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
       if(modelClass.isAssignableFrom(AlbumViewModel::class.java)){
           return AlbumViewModel(mRequestQueue) as T

       }
        throw java.lang.IllegalArgumentException("Unknown class")
    }
}