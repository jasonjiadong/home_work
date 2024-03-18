package com.example.booksys2.service

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.booksys2.db.AppDataBase
import com.example.booksys2.entity.BookEntity
import java.lang.Exception

@OptIn(ExperimentalPagingApi::class)
class RemoteMediatorImpl(
    private val api: BookStroeService,
    private val dataBase: AppDataBase
) : RemoteMediator<Int, BookEntity>() {
    private var nextPage :Int =1
    private var totalPage:Int = 0
    private var hasNext = true;
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, BookEntity>
    ): MediatorResult {

        //1.判断loadtype
        //2.请求网页分页数据
        //3.插入数据库
        try {
            Log.d("qin", "RemoteMediatorImpl: ${loadType}")
            //根据loadtype计算页码
            var pageKey = when (loadType) {
                LoadType.REFRESH -> null//
                LoadType.PREPEND ->
                    return MediatorResult.Success(endOfPaginationReached = false)
                LoadType.APPEND -> {
                    Log.d("qin", "load: LoadType.APPEND--->${state.lastItemOrNull()}")
//                    var lastItem: BookEntity =
//                        state.lastItemOrNull() ?: return MediatorResult.Success(
//                            endOfPaginationReached = true
//                        )
//                    lastItem.page
                    if (!hasNext) {
                        return MediatorResult.Success(endOfPaginationReached = true)
                    } else {
                        2
                    }
//                    val lastItem = state.lastItemOrNull() ?: return MediatorResult.Success(
//                        endOfPaginationReached = true
//                    )
//                    lastItem.page//返回当前页
                }
            }
//            var page = pageKey ?: 0
            //需要修改分页的页码  page*state.config.pagesize    state.config.pageSize
            val result = api.getBookList(
                nextPage,
                      state.config.pageSize)
            //总共
            var total = result.result.total
            //当前
            var current = result.result.current
            //每页数量
            var pageSize = state.config.pageSize
            if(current*pageSize>=total){
                hasNext = false
            }else{
                nextPage = current+1
            }
            var bookResult = result.result.records.isEmpty()
           var books =  result.result.records
            var item = books.map {
                BookEntity(
                    id = it.id,
                    title = it.title,
                    author = it.author,
                    description = it.description,
                    page = 1,
                    coverImage = it.coverImage
                )
            }
            Log.d("qin", "result_item: $item")
            var dao = dataBase.bookDao()
            dataBase.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    dao.clearBookStore()
                }
                dao.insertBook(item)
            }
            return MediatorResult.Success(bookResult)
        } catch (e: Exception) {
            Log.d("qin", "load: 走错了${e.printStackTrace()}")
            return MediatorResult.Error(e)
        }
    }
}