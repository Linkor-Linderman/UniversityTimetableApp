package com.example.universitytimetableapp.common

import android.content.Context
import com.example.universitytimetableapp.R
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MessageSource @Inject constructor(
    @ApplicationContext context: Context
) {
    private val resources = context.resources

    fun getMessage(reason: Int): String {
        return when(reason) {
            WRONG_EMAIL_FORMAT -> resources.getString(R.string.wrong_email_format)
            WRONG_PASSWORD_FORMAT -> resources.getString(R.string.wrong_password_format)
            WRONG_PASSWORD_LENGTH -> resources.getString(R.string.wrong_password_length)
            PASSWORD_NOT_EQUAL_WITH_CONFIRM -> resources.getString(R.string.password_not_equal_with_confirm)
            DO_NOT_CHOOSING_ITEM -> resources.getString(R.string.do_not_choosing_item)
            MESSAGE_FOR_ADMIN_SCHEDULE_WRITER -> resources.getString(R.string.for_admin_schedule_writer)
            else -> ""
        }
    }

    companion object {
        const val WRONG_EMAIL_FORMAT = 0
        const val WRONG_PASSWORD_FORMAT = 1
        const val WRONG_PASSWORD_LENGTH = 2
        const val PASSWORD_NOT_EQUAL_WITH_CONFIRM = 3
        const val DO_NOT_CHOOSING_ITEM = 4
        const val MESSAGE_FOR_ADMIN_SCHEDULE_WRITER = 5
    }
}