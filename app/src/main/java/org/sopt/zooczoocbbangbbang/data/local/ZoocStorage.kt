package org.sopt.zooczoocbbangbbang.data.local

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

const val TOKEN = "token"
const val IS_USER = "isUser"
const val AUTH = "auth"
const val FAMILY_ID = "family_id"

object ZoocStorage {
    private lateinit var loginPreferences: SharedPreferences

    fun init(context: Context) {
        loginPreferences = context.getSharedPreferences(AUTH, Context.MODE_PRIVATE)
    }

    var isUser: Boolean
        get() = loginPreferences.getBoolean(IS_USER, false)
        set(value) = loginPreferences.edit { putBoolean(IS_USER, value).apply() }

    var token: String?
        get() = loginPreferences.getString(TOKEN, null)
        set(value) = loginPreferences.edit { putString(TOKEN, value).apply() }

    var familyId: Int
        get() = loginPreferences.getInt(FAMILY_ID, 0)
        set(value) = loginPreferences.edit { putInt(FAMILY_ID, value).apply() }

    fun clear() {
        loginPreferences.edit { clear().apply() }
    }
}
