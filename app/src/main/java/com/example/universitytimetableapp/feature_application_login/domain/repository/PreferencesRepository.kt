package com.example.universitytimetableapp.feature_application_login.domain.repository

import com.example.universitytimetableapp.feature_application_login.domain.model.UserSettings

interface PreferencesRepository {

    fun putToken(token: String)

    fun getSettings(): UserSettings

    fun putSettings(userSettings: UserSettings)
}