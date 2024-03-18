package com.example.booksys2.service

import com.example.booksys2.bean.BaseResponse
import com.example.booksys2.bean.BookItemBean
import com.example.booksys2.bean.CommonResBean
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface BookStroeService {
    @GET("books/list")
    suspend fun getBookList(
        @Query("pageNo") pageNo: Int,
        @Query("pageSize") pageSize: Int
    ): BaseResponse<BookItemBean>

    @POST("books/add")
    suspend fun insertBook(
        @Body itemBook: BookItemBean
    ): CommonResBean

    @PUT("books/edit")
    suspend fun editBook(
        @Body itemBook: BookItemBean
    ): CommonResBean


    @DELETE("books/delete")
    suspend fun deledtBookById(@Query("id") id: String): CommonResBean
}