package com.example.explore.ui.viewModel

import com.example.explore.model.Category
import com.example.explore.model.FunMenu


data class ExploreUiState(
    val categoryData: List<Category> = emptyList(),
    val currentCategory: Category? = null,
    val currentFunMenu: FunMenu? = null
)
