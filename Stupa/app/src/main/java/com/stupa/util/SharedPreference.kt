package com.stupa.util

import android.content.SharedPreferences
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SharedPreference : KoinComponent {

    private val mSharedPreferences: SharedPreferences by inject()

    fun putString(key: String, data: String?) {
        mSharedPreferences.edit().putString(key, data).apply()
    }

    fun getString(key: String): String? {
        return mSharedPreferences.getString(key, null)
    }

    fun putFloat(key: String, data: Float?) {
        mSharedPreferences.edit().putFloat(key, data!!).apply()
    }

    fun getFloat(key: String): Float {
        return mSharedPreferences.getFloat(key, 0F)
    }

    fun putInt(key: String, data: Int?) {
        mSharedPreferences.edit().putInt(key, data!!).apply()
    }

    fun getInt(key: String,defaultValue:Int=0): Int {
        return mSharedPreferences.getInt(key, defaultValue)
    }

    fun putBoolean(key: String, data: Boolean) {
        mSharedPreferences.edit().putBoolean(key, data).apply()
    }

    fun getBoolean(key: String): Boolean {
        return mSharedPreferences.getBoolean(key, false)
    }

    fun clearPreference() {
        mSharedPreferences.edit().clear().apply()
    }
}