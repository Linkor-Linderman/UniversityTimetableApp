package com.example.universitytimetableapp.feature_schedule.di

import com.example.universitytimetableapp.feature_schedule.data.remote.ScheduleApiService
import com.example.universitytimetableapp.feature_schedule.data.repository.ScheduleFeatureRepositoryImpl
import com.example.universitytimetableapp.feature_schedule.domain.repository.ScheduleFeatureRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ScheduleFeatureModule {

    @Provides
    @Singleton
    fun provideDictionaryApi(): ScheduleApiService {
        return Retrofit
            .Builder()
            .baseUrl(ScheduleApiService.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ScheduleApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideScheduleFeatureRepository(
        api: ScheduleApiService
    ): ScheduleFeatureRepository{
        return ScheduleFeatureRepositoryImpl(
            api
        )
    }
}