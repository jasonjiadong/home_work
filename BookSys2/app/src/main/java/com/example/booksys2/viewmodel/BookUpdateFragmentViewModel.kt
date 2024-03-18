package com.example.booksys2.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.booksys2.bean.BookItemBean
import com.example.booksys2.bean.CommonResBean
import com.example.booksys2.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookUpdateFragmentViewModel @Inject constructor(private val repository: Repository) :
    BaseViewModel() {

    private var _insertBook: MutableLiveData<CommonResBean> = MutableLiveData<CommonResBean>()
    var insertBook: LiveData<CommonResBean> = _insertBook

    private var _updateBook: MutableLiveData<CommonResBean> = MutableLiveData<CommonResBean>()
    var updateBook: LiveData<CommonResBean> = _updateBook
    fun updateOrDeleteBook(add: Boolean, book: BookItemBean) {
        viewModelScope.launch {
            if (add) {
                repository.insertBook(book)
                    .collect { item ->
                        Log.d("qin", "updateOrDeleteBook: ")
                        _insertBook.value = item
                    }
            } else {
                Log.d("qin", "updateOrDeleteBook: false")
                repository.updateBook(book)
                    .collectLatest {
                        Log.d("qin", "updateOrDeleteBook: false_1")
                        _updateBook.value = it
                    }
            }
        }

    }
}