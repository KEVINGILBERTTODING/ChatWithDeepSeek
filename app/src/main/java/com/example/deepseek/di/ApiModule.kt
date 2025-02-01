package com.example.deepseek.di

import com.example.deepseek.data.apis.DeepSeekApis
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {
    @Provides
    @Singleton
    fun provideDeepSeekApis(retrofit: Retrofit) : DeepSeekApis {
        return retrofit.create(DeepSeekApis::class.java)
    }
}