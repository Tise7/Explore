package com.example.explore.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringSetPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class FavoriteDataStoreManager(private val dataStore: DataStore<Preferences>) {

    companion object {
        private const val FAVORITE_FUNMENUS_KEY = "favorite_funmenus"
        val FAVORITE_FUNMENUS_PREF_KEY = stringSetPreferencesKey(FAVORITE_FUNMENUS_KEY)
    }

    suspend fun saveFavoriteFunMenus(favoriteIds: Set<Long>) {
        dataStore.edit { preferences ->
            preferences[FAVORITE_FUNMENUS_PREF_KEY] = favoriteIds.map { it.toString() }.toSet()
        }
    }

    val favoriteFunMenus: Flow<Set<Long>> = dataStore.data.map { preferences ->
        preferences[FAVORITE_FUNMENUS_PREF_KEY] ?.map { it.toLong() }?.toSet() ?: emptySet()
    }
}