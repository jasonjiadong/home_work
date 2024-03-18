package com.example.booksys2.di

import com.example.booksys2.db.AppDataBase
import com.example.booksys2.mapper.BookEntity2ItemBean
import com.example.booksys2.repository.Repository
import com.example.booksys2.repository.RepositoryImpl
import com.example.booksys2.service.BookStroeService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.FragmentScoped
import dagger.hilt.android.scopes.ViewModelScoped

@InstallIn(ViewModelComponent::class)
@Module
object RepositoryModul {
    @ViewModelScoped
    @Provides
    fun provideBookRepository(api:BookStroeService, appDataBase: AppDataBase): Repository {
        return RepositoryImpl(api,appDataBase,BookEntity2ItemBean())
    }

}