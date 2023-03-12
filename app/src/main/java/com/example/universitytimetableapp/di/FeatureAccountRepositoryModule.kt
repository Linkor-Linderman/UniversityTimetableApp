package com.example.universitytimetableapp.di

import com.example.universitytimetableapp.feature_profile.data.repository.AccountRepositoryImpl
import com.example.universitytimetableapp.feature_profile.domain.repository.AccountRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class FeatureAccountRepositoryModule {

    @Singleton
    @Binds
    abstract fun provideAccountRepository(
        repository: AccountRepositoryImpl
    ): AccountRepository
}