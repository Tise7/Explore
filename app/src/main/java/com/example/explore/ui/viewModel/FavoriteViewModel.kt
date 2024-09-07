package com.example.explore.ui.viewModel

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.explore.R
import com.example.explore.data.FavoriteDataStoreManager
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

//
//class FavoriteViewModel(
//    private val favoriteDataStoreManager: FavoriteDataStoreManager
//): ViewModel() {
//
//    val favoriteUiState: StateFlow<FavoriteUiState> =
//        favoriteDataStoreManager.isFavorite.map { isFavorite ->
//            FavoriteUiState(isFavorite = isFavorite)
//        }.stateIn(
//            scope = viewModelScope,
//            started = SharingStarted.WhileSubscribed(5_000),
//            initialValue = FavoriteUiState()
//        )
//
//    fun saveFavorite(isFavorite: Boolean) {
//        viewModelScope.launch {
//            favoriteDataStoreManager.saveFavoriteFunMenus(isFavorite)
//        }
//    }
//}
//
//data class FavoriteUiState(
//    val isFavorite: Boolean = true,
//    val toggleContentDescription: Int =
//        if (isFavorite) R.string.add_favorite else R.string.remove_favorite,
//    val toggleIcon: ImageVector =
//        if (isFavorite) Icons.Filled.Favorite else Icons.Default.FavoriteBorder,
//)




