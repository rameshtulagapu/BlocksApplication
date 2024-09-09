package com.harunbekcan.cleanarchitecturecomposeproject.di

import com.example.blocksapplication.data.remote.Service
import com.example.blocksapplication.data.repository.BlocksRepositoryImpl
import com.example.blocksapplication.domain.repository.BlocksRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideUsersRepository(service: Service) : BlocksRepository = BlocksRepositoryImpl(service)
}