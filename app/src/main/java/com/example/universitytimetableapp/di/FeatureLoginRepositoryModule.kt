package com.example.universitytimetableapp.di

import com.example.universitytimetableapp.feature_application_login.data.repository.EntranceRepositoryImpl
import com.example.universitytimetableapp.feature_application_login.data.repository.ExistingGroupsTeachersRepositoryImpl
import com.example.universitytimetableapp.feature_application_login.domain.repository.EntranceRepository
import com.example.universitytimetableapp.feature_application_login.domain.repository.ExistingGroupsTeachersRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class FeatureLoginRepositoryModule {

    @Singleton
    @Binds
    abstract fun provideEntranceRepository(
        repository: EntranceRepositoryImpl
    ): EntranceRepository

    @Singleton
    @Binds
    abstract fun provideExistingGroupsTeachersRepository(
        repository: ExistingGroupsTeachersRepositoryImpl
    ): ExistingGroupsTeachersRepository
}