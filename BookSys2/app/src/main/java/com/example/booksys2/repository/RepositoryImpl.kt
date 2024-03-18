package com.example.booksys2.repository

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.booksys2.bean.BookItemBean
import com.example.booksys2.bean.CommonResBean
import com.example.booksys2.db.AppDataBase
import com.example.booksys2.entity.BookEntity
import com.example.booksys2.mapper.BookItemBean2BookEntity
import com.example.booksys2.mapper.Mapper
import com.example.booksys2.service.BookStroeService
import com.example.booksys2.service.RemoteMediatorImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map


class RepositoryImpl(
    private var api: BookStroeService,
    private val appDataBase: AppDataBase,
    private val bookEntity2ItemBean: Mapper<BookEntity, BookItemBean>
) : Repository {
    @OptIn(ExperimentalPagingApi::class)
    override fun getBookList(): Flow<PagingData<BookItemBean>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                prefetchDistance = 1,
                initialLoadSize = 10
            ), remoteMediator = RemoteMediatorImpl(api, appDataBase)//网络拿数据
        ) {
            appDataBase.bookDao().getBooks()//本地拿数据

        }.flow.catch {
            Log.d("qin", "getBookList___: ${it.printStackTrace()}")
        }.flowOn(Dispatchers.IO).map { paginData ->
            paginData.map {
                bookEntity2ItemBean.map(it)
            }
        }
    }


    override fun deleBookById(id: String): Flow<CommonResBean> {
        return flow<CommonResBean> {
            var del = api.deledtBookById(id)
            if (del.success) {
                appDataBase.bookDao().deleteBook(id)
            }
            Log.d("qin", "deleBookById: 执行删除接口")
        }.catch {
            Log.d("qin", "deleBookById: 执行删除接口 1${it.printStackTrace()}")
        }.flowOn(Dispatchers.IO)
    }

    override fun insertBook(insertBook: BookItemBean): Flow<CommonResBean> {
        return flow<CommonResBean> {
           var result =  api.insertBook(insertBook)
            if(result.success){
               var bookEntity =  BookItemBean2BookEntity().map(insertBook)
                appDataBase.bookDao().insertBook(listOf(bookEntity))
            }
            emit(result)
            Log.d("qin", "insertBook: ")
        }.catch {
            Log.d("qin", "insertBook: ${it.printStackTrace()}")
        }.flowOn(Dispatchers.IO)
    }

    override fun updateBook(itemBook: BookItemBean): Flow<CommonResBean> {
        return flow<CommonResBean> {
           var result =  api.editBook(itemBook)
            if(result.success){
                var bookEntity =  BookItemBean2BookEntity().map(itemBook)
                appDataBase.bookDao().updateBook(bookEntity)
            }
            emit(result)
            Log.d("qin", "updateBook: $result")
        }.catch {
            Log.d("qin", "updateBook: catch: ${it.printStackTrace()}")
        }.flowOn(Dispatchers.IO)
    }
}