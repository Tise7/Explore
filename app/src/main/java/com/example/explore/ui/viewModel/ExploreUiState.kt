package com.example.explore.ui.viewModel

import com.example.explore.data.Category
import com.example.explore.data.FunMenu


data class ExploreUiState(
    val categoryData: List<Category> = emptyList(),
    val currentCategory: Category? = null,
    val currentFunMenu: FunMenu? = null
)
