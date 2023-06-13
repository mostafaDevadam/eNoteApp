package com.example.mynavviewmodelapplication.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.volley.RequestQueue
import com.example.mynavviewmodelapplication.models.Post

class PostViewModelFactory(var mRequestQueue: RequestQueue): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(PostViewModel::class.java)){
            return PostViewModel(mRequestQueue) as T
        }
        throw  java.lang.IllegalArgumentException("Unknown class")
    }

}