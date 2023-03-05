package com.example.universitytimetableapp.di

import com.example.universitytimetableapp.feature_application_login.data.repository.ChoiceRepositoryImpl
import com.example.universitytimetableapp.feature_application_login.data.repository.EntranceRepositoryImpl
import com.example.universitytimetableapp.feature_application_login.data.repository.PreferencesRepositoryImpl
import com.example.universitytimetableapp.feature_application_login.domain.repository.ChoiceRepository
import com.example.universitytimetableapp.feature_application_login.domain.repository.EntranceRepository
import com.example.universitytimetableapp.feature_application_login.domain.repository.PreferencesRepository
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
    abstract fun provideChoiceRepository(
        repository: ChoiceRepositoryImpl
    ): ChoiceRepository

    @Singleton
    @Binds
    abstract fun providePreferencesRepository(
        repository: PreferencesRepositoryImpl
    ): PreferencesRepository
}