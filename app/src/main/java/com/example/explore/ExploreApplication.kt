package com.example.explore

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.explore.data.FavoriteDataStoreManager


private const val FAVORITE_NAME = "favorites"
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
    name = FAVORITE_NAME
)

class ExploreApplication : Application() {
    /**
     * AppContainer instance used by the rest of classes to obtain dependencies
     */
    lateinit var favoriteDataStoreManager: FavoriteDataStoreManager

    override fun onCreate() {
        super.onCreate()
        favoriteDataStoreManager = FavoriteDataStoreManager(dataStore)
    }
}
