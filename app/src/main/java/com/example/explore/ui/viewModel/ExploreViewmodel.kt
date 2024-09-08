package com.example.explore.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.explore.data.AppImageResources
import com.example.explore.data.Category
import com.example.explore.data.FunListDataProvider
import com.example.explore.data.FunMenu
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.random.Random


class ExploreViewmodel
//    (private val favoriteDataStoreManager: FavoriteDataStoreManager)
: ViewModel() {
    private val _exploreUiState = MutableStateFlow(ExploreUiState())
    val exploreUiState: StateFlow<ExploreUiState> = _exploreUiState

    init {
        initializeUiState()
        startImageRotation()
//        loadFavoriteFunMenus()
    }


    private fun initializeUiState() {
        _exploreUiState.update {
            ExploreUiState(
                categoryData = FunListDataProvider.categoryList,
                currentCategory = it.currentCategory ?: FunListDataProvider.categoryList.first(),
                currentFunMenu = it.currentFunMenu ?: FunListDataProvider.categoryList.first().categoryList.first()
            )
        }
    }

    private var _isLandscape = MutableStateFlow(false)
    val isLandscape: StateFlow<Boolean> = _isLandscape.asStateFlow()

    fun updateOrientation(isLandscape: Boolean) {
        _isLandscape.value = isLandscape
    }

    private val _currentImageResource = MutableStateFlow(AppImageResources.images.resources.first())
    val currentImageResource: StateFlow<Int> = _currentImageResource.asStateFlow()

    private fun startImageRotation() {
        viewModelScope.launch {
            while (true) {
                delay(10000)
                val randomIndex = Random.nextInt(AppImageResources.images.resources.size)
                _currentImageResource.value = AppImageResources.images.resources[randomIndex]
            }
        }
    }


    fun updateCurrentCategory(category: Category) {
        _exploreUiState.update { currentState ->
            currentState.copy(currentCategory = category)
        }
    }

    fun updateCurrentFunMenu(funMenu: FunMenu?) {
        _exploreUiState.update { currentState ->
            currentState.copy(currentFunMenu = funMenu)
        }
    }

    private val _favoriteFunMenus = MutableStateFlow<Set<FavoriteKey>>(emptySet())
    val favoriteFunMenus: StateFlow<Set<FavoriteKey>> = _favoriteFunMenus.asStateFlow()

    fun updateFunMenu(category: Category, updatedFunMenu: FunMenu) {
        viewModelScope.launch {
            val key = FavoriteKey(category.id, updatedFunMenu.id)
            if (key in _favoriteFunMenus.value) {
                _favoriteFunMenus.value -= key
            } else {
                _favoriteFunMenus.value += key
            }
        }
    }

//    fun toggleFavorite(funMenuId: Long) {
//        viewModelScope.launch {
//            val updatedCategoryData = _exploreUiState.value.categoryData.map { category ->
//                category.copy(categoryList = category.categoryList.map { funMenu ->
//                    if (funMenu.id == funMenuId) {
//                        funMenu.copy(isFavorite = !funMenu.isFavorite)
//                    } else {
//                        funMenu
//                    }
//                })
//            }
//            _exploreUiState.update { it.copy(categoryData = updatedCategoryData) }
//        }
//    }
//
//    fun updateFunMenu(updatedFunMenu: FunMenu) {
//        viewModelScope.launch {
//            val categoryToUpdate = _exploreUiState.value.categoryData.find {
//                it.categoryList.any { funMenu -> funMenu.id == updatedFunMenu.id }
//            }
//            if (categoryToUpdate != null) {
//                // ... update the category and then update the _exploreUiState
//                val updatedCategoryList = _exploreUiState.value.categoryData.map { category ->
//                    if (category.categoryList.any { it.id == updatedFunMenu.id }) {
//                        category.copy(
//                            categoryList = category.categoryList.map {
//                                if (it.id == updatedFunMenu.id) updatedFunMenu else it
//                            }
//                        )
//                    } else {
//                        category
//                    }
//                }
//                _exploreUiState.value = _exploreUiState.value.copy(categoryData = updatedCategoryList)
//            }
//            saveFavoriteFunMenus()
//        }
//    }
//
//    private fun loadFavoriteFunMenus() {
//        viewModelScope.launch {
//            favoriteDataStoreManager.favoriteFunMenus.collect { favoriteIds ->
//                updateFunMenusWithFavorites(favoriteIds)
//            }
//        }
//    }
//
//    private suspend fun saveFavoriteFunMenus() {
//        val favoriteIds = _exploreUiState.value.categoryData.flatMap { it.categoryList }
//            .filter { it.isFavorite }
//            .map { it.id }
//            .toSet()
//        favoriteDataStoreManager.saveFavoriteFunMenus(favoriteIds)
//    }
//
//
//    private fun updateFunMenusWithFavorites(favoriteIds: Set<Long>) {
//        _exploreUiState.update { currentState ->
//            currentState.copy(categoryData = currentState.categoryData.map { category ->
//                category.copy(categoryList = category.categoryList.map { funMenu ->
//                    if (funMenu.isFavorite != favoriteIds.contains(funMenu.id)) {
//                        funMenu.copy(isFavorite = favoriteIds.contains(funMenu.id))
//                    } else {
//                        funMenu
//                    }
//                })
//            })
//        }
//    }

}

data class FavoriteKey(val categoryId: Int, val funMenuId: Long)