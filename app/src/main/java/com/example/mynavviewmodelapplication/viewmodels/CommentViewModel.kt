package com.example.mynavviewmodelapplication.viewmodels

import androidx.databinding.Observable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.RequestQueue
import com.example.mynavviewmodelapplication.models.Comment
import com.example.mynavviewmodelapplication.repository.CommentRepository

class CommentViewModel(var mRequestQueue: RequestQueue): ViewModel(), Observable {
    var onlineComments = MutableLiveData<ArrayList<Comment>>()
    var commentRepo = CommentRepository(mRequestQueue)

    init {

    }

    fun getAllCommentsByPostId(postId: String){
        commentRepo.fetchAllCommentsByPostId(postId)
        onlineComments = commentRepo.comments
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }
}