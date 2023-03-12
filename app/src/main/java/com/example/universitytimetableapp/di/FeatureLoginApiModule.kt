package com.example.universitytimetableapp.di

import com.example.universitytimetableapp.feature_application_login.data.api.ChoiceApi
import com.example.universitytimetableapp.feature_application_login.data.api.EntranceApi
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
    fun provideChoiceApi(retrofit: Retrofit): ChoiceApi =
        retrofit.create(ChoiceApi::class.java)
}