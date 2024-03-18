package com.example.booksys2.db

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.booksys2.entity.BookEntity

@Dao
interface BookDao {
    @Query("DELETE FROM BookEntity")
    suspend fun clearBookStore()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBook(booList:List<BookEntity>)

    @Query("SELECT * FROM BookEntity")
    fun getBooks(): PagingSource<Int,BookEntity>

    @Query("DELETE FROM BookEntity WHERE id = :bookId")
    fun deleteBook(bookId: String)

    @Update
    suspend fun updateBook(bookEntity: BookEntity)

}