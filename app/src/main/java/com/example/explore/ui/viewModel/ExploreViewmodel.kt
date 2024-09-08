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

    private val _favoriteFunMenus = MutableStateFlow<Set<FavoriteKey>>(emptySet())
    val favoriteFunMenus: StateFlow<Set<FavoriteKey>> = _favoriteFunMenus.asStateFlow()

    fun updateFavoriteFunMenu(category: Category, updatedFavoriteFunMenu: FunMenu) {
        viewModelScope.launch {
            val key = FavoriteKey(category.id, updatedFavoriteFunMenu.id)
            if (key in _favoriteFunMenus.value) {
                _favoriteFunMenus.value -= key
            } else {
                _favoriteFunMenus.value += key
            }
        }
    }
}

