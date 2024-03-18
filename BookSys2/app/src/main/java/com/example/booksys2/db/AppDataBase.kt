package com.example.booksys2.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.booksys2.entity.BookEntity

@Database(
    entities = [BookEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDataBase :RoomDatabase(){
    abstract fun bookDao():BookDao
}