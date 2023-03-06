package com.example.universitytimetableapp.feature_application_login.data.repository

import com.example.universitytimetableapp.common.Constants
import com.example.universitytimetableapp.common.SharedPref
import com.example.universitytimetableapp.feature_application_login.domain.model.UserSettings
import com.example.universitytimetableapp.feature_application_login.domain.repository.PreferencesRepository
import javax.inject.Inject

class PreferencesRepositoryImpl @Inject constructor(
    private val sharedPref: SharedPref
) : PreferencesRepository {

    override fun putToken(token: String) {
        sharedPref.setString(Constants.TOKEN, token)
    }

    override fun getSettings(): UserSettings {
        return UserSettings(
            role = sharedPref.getString(Constants.TYPE_USER) ?: "",
            idChosenItem = sharedPref.getString(Constants.ID) ?: "",
            nameChosenItem = sharedPref.getString(Constants.TEACHER_GROUP_NAME) ?: ""
        )
    }

    override fun putSettings(userSettings: UserSettings) {
        sharedPref.setString(Constants.TYPE_USER, userSettings.role)
        sharedPref.setString(Constants.ID, userSettings.idChosenItem)
        sharedPref.setString(Constants.TEACHER_GROUP_NAME, userSettings.nameChosenItem)
    }
}