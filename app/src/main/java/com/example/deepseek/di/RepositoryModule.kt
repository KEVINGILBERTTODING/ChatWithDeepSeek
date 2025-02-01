package com.example.deepseek.di

import com.example.deepseek.data.apis.DeepSeekApis
import com.example.deepseek.domain.repositories.ChatRepository
import com.example.deepseek.domain.repositoryimpl.ChatRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun bindChatRepository(apis: DeepSeekApis) : ChatRepository {
        return ChatRepositoryImpl(apis)
    }
}