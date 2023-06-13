package com.example.mynavviewmodelapplication.viewmodels
import android.util.Log
import androidx.databinding.Observable
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.android.volley.RequestQueue

import androidx.lifecycle.ViewModel
import com.example.mynavviewmodelapplication.models.Post
import com.example.mynavviewmodelapplication.repository.PostRepository

class PostViewModel(var mRequestQueue: RequestQueue): ViewModel(), Observable {
    var onlinePosts = MutableLiveData<ArrayList<Post>>()
    var postRepo = PostRepository(mRequestQueue)
    var postTitle = MutableLiveData<String>("test...post..title")
    var onlinePost = MutableLiveData<Post>()
    var onlinePosts_ = MutableLiveData<ArrayList<Post>>()


    init {
        postRepo.fetchAllPosts()
        onlinePosts = postRepo.posts
    }

    fun getOnePostByID(id: String){
        postRepo.fetOnePostById(id)
        onlinePost = postRepo.post
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }
}