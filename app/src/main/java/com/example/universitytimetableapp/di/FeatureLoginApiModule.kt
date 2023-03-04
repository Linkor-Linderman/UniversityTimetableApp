package com.example.universitytimetableapp.di

import com.example.universitytimetableapp.feature_application_login.data.api.EntranceApi
import com.example.universitytimetableapp.feature_application_login.data.api.ExistingGroupsTeachersApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FeatureLoginApiModule {

    @Singleton
    @Provides
    fun provideEntranceApi(retrofit: Retrofit): EntranceApi =
        retrofit.create(EntranceApi::class.java)

    @Singleton
    @Provides
    fun provideExistingGroupsTeachersApi(retrofit: Retrofit): ExistingGroupsTeachersApi =
        retrofit.create(ExistingGroupsTeachersApi::class.java)
}