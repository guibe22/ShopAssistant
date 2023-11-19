package com.wgg.shopassistant.util

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import java.io.IOException


private const val LAYOUT_PREFERENCES_NAME = "layout_preferences"


val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
    name = LAYOUT_PREFERENCES_NAME
)

class SettingsDataStore(preferenceDataStore: DataStore<Preferences>) {
    private val DARK_MODE = booleanPreferencesKey("dark_mode")

    val preferenceFlow: Flow<Boolean> = preferenceDataStore.data
        .catch {
            if (it is IOException) {
                it.printStackTrace()
                emit(emptyPreferences())
            } else {
                throw it
            }
        }
        .map { preferences ->
            preferences[DARK_MODE] ?: false
        }

    suspend fun saveDarkModeToPreferencesStore(isDarkMode: Boolean, context: Context) {
        context.dataStore.edit { preferences ->
            preferences[DARK_MODE] = isDarkMode
        }
    }
    suspend fun getDarkModePreference(context: Context): Boolean {
        return context.dataStore.data.first()[DARK_MODE] ?: false
    }
}
