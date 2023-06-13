package com.example.mynavviewmodelapplication.viewmodels

import androidx.databinding.Observable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.RequestQueue
import com.example.mynavviewmodelapplication.models.User
import com.example.mynavviewmodelapplication.repository.UserRepository

class UserViewModel(var mRequestQueue: RequestQueue): ViewModel(), Observable {

  var onlineUser = MutableLiveData<User>()
  var userRepo = UserRepository(mRequestQueue)

  init {
    getOneUserId(1)
  }

    fun getOneUserId(id: Int){
        userRepo.fetchOneUserById(id)
        onlineUser = userRepo.user
    }








    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }
}