package com.example.hw2_m6_2.di

import com.example.hw2_m6_2.data.CartoonApiService
import com.example.hw2_m6_2.data.NetworkService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return NetworkService.createRetrofit(okHttpClient)
    }

    @Provides
    fun provideOkHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient {
        return NetworkService.createOkHttpClient(interceptor)
    }


    @Provides
    fun provideInterceptor(): HttpLoggingInterceptor {
        return NetworkService.createLoggingInterceptor()
    }
    @Provides
    fun provideCartoonApiService(retrofit: Retrofit): CartoonApiService = retrofit.create(CartoonApiService::class.java)
}