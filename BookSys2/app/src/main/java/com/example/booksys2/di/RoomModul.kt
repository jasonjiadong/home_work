package com.example.booksys2.di

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import com.example.booksys2.db.AppDataBase
import com.example.booksys2.db.BookDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RoomModul {
    @Singleton
    @Provides
    fun provideAppDataBase(application: Application): AppDataBase {
        return Room.databaseBuilder(application,AppDataBase::class.java,"book.db").build()
    }

    @Singleton
    @Provides
    fun provideBookDao(database: AppDataBase) : BookDao{
        return database.bookDao()
    }

}