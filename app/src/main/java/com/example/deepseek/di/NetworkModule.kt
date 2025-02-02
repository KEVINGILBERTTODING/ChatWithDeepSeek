package com.example.deepseek.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.deepseek.base.constant.ServerInfo
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Provides
    @Singleton
    fun provideOkHttpClient(context: Context) : OkHttpClient {
        val chuckerInterceptor = ChuckerInterceptor.Builder(context)
            .build()
        return OkHttpClient.Builder()
            .connectTimeout(300, TimeUnit.SECONDS)
            .readTimeout(300, TimeUnit.SECONDS)
            .writeTimeout(300, TimeUnit.SECONDS)
            .addInterceptor(chuckerInterceptor)
            .build()
    }
    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient) : Retrofit {
        return Retrofit.Builder()
            .baseUrl(ServerInfo.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}