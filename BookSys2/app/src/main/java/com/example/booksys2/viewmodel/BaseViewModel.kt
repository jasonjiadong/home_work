package com.example.booksys2.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel:ViewModel() {
     private val _refreshData:MutableLiveData<Boolean> = MutableLiveData()
    var refreshData:LiveData<Boolean> = _refreshData


    fun setData(boolean: Boolean){
        _refreshData.value = boolean
        Log.d("qin", "BaseViewModel-setData: $boolean")
    }
}