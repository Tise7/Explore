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
























//
//class FavoriteDataStoreManager(
//    private val dataStore: DataStore<Preferences>
//) {
//    companion object {
//        val FAVORITE_FUNMENUS_KEY = booleanPreferencesKey("is_favorite")
//        const val TAG = "FavoritesRepo"
//
//    }
//
//    suspend fun saveFavoriteFunMenus(isFavorite: Boolean) {
//       dataStore.edit { preferences ->
//            preferences[FAVORITE_FUNMENUS_KEY] = isFavorite
//        }
//    }
//
//    val isFavorite: Flow<Boolean> = dataStore.data
//        .catch {
//            if(it is IOException) {
//                Log.e(TAG, "Error reading preferences.", it)
//                emit(emptyPreferences())
//            } else {
//                throw it
//            }
//        }
//        .map { preferences ->
//            preferences[FAVORITE_FUNMENUS_KEY] ?: true
//        }


//
//    suspend fun addFavoriteFunMenu(funMenuId: Long) {
//        context.dataStore.edit { preferences ->
//            val currentFavorites = preferences[FAVORITE_FUNMENUS_KEY]
//            if (currentFavorites != null) {
//                preferences[FAVORITE_FUNMENUS_KEY] = currentFavorites + funMenuId
//            }
//        }
//    }
//
//    suspend fun removeFavoriteFunMenu(funMenuId: Long) {
//        context.dataStore.edit { preferences ->
//            val currentFavorites = preferences[FAVORITE_FUNMENUS_KEY]
//            if (currentFavorites != null) {
//                preferences[FAVORITE_FUNMENUS_KEY] = currentFavorites - funMenuId
//            }
//        }
//    }
//}