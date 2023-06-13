package com.example.mynavviewmodelapplication.viewmodels

import androidx.databinding.Observable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FeedsViewModel: ViewModel(), Observable {

    var title = MutableLiveData<String>("my title")
    var content = MutableLiveData<String>("content...")

    fun setTitle(title_: String){
        title.value = title_
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }
}