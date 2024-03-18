package com.example.booksys2.repository

import androidx.paging.PagingData
import com.example.booksys2.bean.BookItemBean
import com.example.booksys2.bean.CommonResBean
import kotlinx.coroutines.flow.Flow

interface Repository {
    fun getBookList(): Flow<PagingData<BookItemBean>>
    fun insertBook(itemBook: BookItemBean):Flow<CommonResBean>
    fun deleBookById(id:String):Flow<CommonResBean>
    fun updateBook(itemBook: BookItemBean):Flow<CommonResBean>
}