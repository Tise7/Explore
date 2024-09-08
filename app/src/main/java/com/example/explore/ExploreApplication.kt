package com.example.explore

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.datastore.preferences.preferencesDataStoreFile
import com.example.explore.data.FavoriteDataStoreManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


private const val FAVORITE_NAME = "favorites"
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
    name = FAVORITE_NAME
)


@HiltAndroidApp
class ExploreApplication : Application() {

//    @Module
//    @InstallIn(SingletonComponent::class)
//    object DataStoreModule {
//
//        @Provides
//        @Singleton
//        fun providesDataStore(@ApplicationContext appContext: Context): DataStore<Preferences> =
//            PreferenceDataStoreFactory.create(
//                produceFile = { appContext.preferencesDataStoreFile(FAVORITE_NAME) }
//            )
//
//        @Provides
//        @Singleton
//        fun providesFavoriteDataStoreManager(dataStore: DataStore<Preferences>): FavoriteDataStoreManager =
//            FavoriteDataStoreManager(dataStore)
//    }

    /**
     * AppContainer instance used by the rest of classes to obtain dependencies
     */
    lateinit var favoriteDataStoreManager: FavoriteDataStoreManager

    override fun onCreate() {
        super.onCreate()
        favoriteDataStoreManager = FavoriteDataStoreManager(dataStore)
    }
}
