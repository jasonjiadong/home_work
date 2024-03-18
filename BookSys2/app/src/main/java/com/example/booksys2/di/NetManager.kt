package com.example.booksys2.di

import android.app.Application
import android.util.Log
import com.example.booksys2.service.BookStroeService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetManager {
    private const val SERVICE_URL = "http://xz3dfa.natappfree.cc/jeecg-boot/"

    @Singleton
    @Provides
    fun provideHttpClient():OkHttpClient{
        val interceptor = HttpLoggingInterceptor {
            Log.d("qin", "provideHttpClient: $it")
        }.apply { level = HttpLoggingInterceptor.Level.BODY }
        return OkHttpClient.Builder().addInterceptor(interceptor).build()
    }
    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient):Retrofit{
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(SERVICE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideBookStoreService(retrofit: Retrofit): BookStroeService {
        return retrofit.create(BookStroeService::class.java)
    }
}