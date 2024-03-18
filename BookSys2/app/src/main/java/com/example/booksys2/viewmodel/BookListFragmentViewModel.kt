package com.example.booksys2.viewmodel

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.booksys2.R
import com.example.booksys2.bean.BaseResponse
import com.example.booksys2.bean.BookItemBean
import com.example.booksys2.repository.Repository
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class BookListFragmentViewModel @Inject constructor(private val repository:Repository):BaseViewModel() {
    val data:LiveData<PagingData<BookItemBean>> = repository.getBookList().
    cachedIn(viewModelScope).asLiveData()
    var editBookData : MutableLiveData<Bundle> = MutableLiveData()

    var deleResult : MutableLiveData<Boolean> = MutableLiveData(false)

    fun deleBookById(id :String){
        viewModelScope.launch {
            repository.deleBookById(id).collect{
                deleResult.value = it.success
            }
        }
    }
    fun editBook(item: BookItemBean?) {
        Log.d("qin", "editBook: ")
        val editBundle = Bundle()
        editBundle.putBoolean("isAdd", false) // 设置一个名为"itemId"的整数参数
        editBundle.putSerializable("book",item)
        editBookData.value = editBundle
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("qin", "onCleared: ")
    }
}