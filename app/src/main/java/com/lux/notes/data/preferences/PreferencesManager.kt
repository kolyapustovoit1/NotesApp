package com.lux.notes.data.preferences

import android.content.Context
import android.content.SharedPreferences
import com.lux.notes.domain.models.SettingsData

class PreferencesManager(
    context: Context
) {
    private val context: Context = context.applicationContext

    @Volatile
    private var sharedPref: SharedPreferences? = null

    private fun getSharedPerf(): SharedPreferences {
        return sharedPref ?: synchronized(this) {
            context.getSharedPreferences(
                "${context.packageName}.main",
                Context.MODE_PRIVATE
            )
        }
    }

    fun save(data: SettingsData) {
        getSharedPerf()
            .edit()
            .apply { 
                putBoolean(DARK_THEME_KEY, data.darkTheme)
                putBoolean(LIST_VIEW_KEY, data.listView)
                apply()
            }
    }

    fun get(): SettingsData {
        return SettingsData(
            darkTheme = getSharedPerf().getBoolean(DARK_THEME_KEY, false),
            listView = getSharedPerf().getBoolean(LIST_VIEW_KEY, false)
        )
    }

    companion object {
        private const val DARK_THEME_KEY = "DarkTheme"
        private const val LIST_VIEW_KEY = "ListView"
    }
}