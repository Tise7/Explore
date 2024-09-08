

package com.example.explore.ui.viewModel

import android.app.Application
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.explore.ExploreApplication


/**
 * Provides Factory to create instance of ViewModel for the entire Inventory app
 */

object AppViewModelProvider {
    val Factory = viewModelFactory {

        // Initializer for ExploreViewmodel
        initializer {
            ExploreViewmodel(
//                exploreApplication().favoriteDataStoreManager
            )
        }
   }
}

/**
 * Extension function to queries for [Application] object and returns an instance of
 * [ExploreApplication].
 */
fun CreationExtras.exploreApplication(): ExploreApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as ExploreApplication)
