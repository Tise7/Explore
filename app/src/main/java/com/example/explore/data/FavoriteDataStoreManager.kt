package com.example.explore.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.explore.ui.viewModel.FavoriteKey
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map


class FavoriteDataStoreManager(private val dataStore: DataStore<Preferences>) {
    companion object {
        private const val FAVORITE_FUN_MENUS_KEY = "favorite_fun_menus"
        private const val KEY_SEPARATOR = ","
        private const val VALUE_SEPARATOR = ":"
    }

    suspend fun saveFavoriteFunMenus(favoriteKeys: Set<FavoriteKey>) {
        dataStore.edit { preferences ->
            val favoriteFunMenusString = favoriteKeys.joinToString(KEY_SEPARATOR) { key ->
                "${key.categoryId}$VALUE_SEPARATOR${key.funMenuId}"
            }
            preferences[stringPreferencesKey(FAVORITE_FUN_MENUS_KEY)] = favoriteFunMenusString
        }
    }

    suspend fun loadFavoriteFunMenus(): Set<FavoriteKey> {
        return dataStore.data.map { preferences ->
            val favoriteFunMenusString = preferences[stringPreferencesKey(FAVORITE_FUN_MENUS_KEY)] ?: ""
            favoriteFunMenusString.split(KEY_SEPARATOR).mapNotNull { keyString ->
                val parts = keyString.split(VALUE_SEPARATOR)
                if (parts.size == 2) {
                    FavoriteKey(parts[0].toInt(), parts[1].toLong())
                } else {
                    null
                }
            }.toSet()
        }.first()
    }
}