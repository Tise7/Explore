package com.example.explore.ui.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.explore.ui.screen.CategoryList
import com.example.explore.ui.screen.ExpandedCategoryList
import com.example.explore.ui.screen.ExpandedMenuScreen
import com.example.explore.ui.screen.FavoriteScreen
import com.example.explore.ui.screen.FunMenuList
import com.example.explore.ui.screen.NormalDetailScreen
import com.example.explore.ui.screen.WelcomeScreen
import com.example.explore.ui.viewModel.ExploreUiState
import com.example.explore.ui.viewModel.ExploreViewmodel


@Composable
fun ExploreNavigation(
    exploreUiState: ExploreUiState,
    exploreViewmodel: ExploreViewmodel,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val currentImageResource by exploreViewmodel.currentImageResource.collectAsState()
    val isLandscape by exploreViewmodel.isLandscape.collectAsState()


    NavHost(
        navController = navController,
        startDestination = Welcome.route,
        modifier = modifier.fillMaxSize()
    ) {
        composable(route = Welcome.route) {
            WelcomeScreen(
                onNavigateToMenuList = {
                    val nextRoute = if (isLandscape) ExpandedCategory.route else Category.route
                    navController.navigateSingleTopTo(nextRoute)
                }
            )
        }
        composable(route = Favorite.route) {
            FavoriteScreen()
        }

        composable(route = Category.route) {
            CategoryList(
                categories = exploreUiState.categoryData,
                onItemClick = { category ->
                    exploreViewmodel.updateCurrentCategory(category)
                    val nextRoute = if (isLandscape) ExpandedMenu.route else Menu.route
                    navController.navigate(nextRoute)
                }
            )
        }
        composable(route = Menu.route) {
            exploreUiState.currentCategory?.let { it1 ->
                FunMenuList(
                    funMenu = it1.categoryList,
                    onFavoriteClick = { updatedFunMenu ->
                        exploreViewmodel.updateFunMenu(updatedFunMenu)
                    },
                    onItemClick = { funMenu ->
                        exploreViewmodel.updateCurrentFunMenu(funMenu)
                        navController.navigate(Detail.route)
                    }
                )
            }
        }
        composable(route = Detail.route) {
            NormalDetailScreen(
                imageRes = exploreUiState.currentFunMenu?.imageResourceId ?: 0,
                titleRes = exploreUiState.currentFunMenu?.titleResourceId ?: 0,
                descriptionRes = exploreUiState.currentFunMenu?.descriptionResourceId ?: 0
            )
        }
        composable(route = ExpandedCategory.route) {
//            val onCategoryClickRemembered = remember {
//                { category: com.example.funeu.data.Category ->
//                    viewModel.updateCurrentCategory(category)
//                    viewModel.updateCurrentFunMenu(null)
//                    navController.navigate(ExpandedMenu.route)
//                }
//            }
//            ExpandedCategoryList(
//                categories = uiState.categoryData,
//                imageRes = currentImageResource,
//                onCategoryClick = onCategoryClickRemembered
//            )
            ExpandedCategoryList(
                categories = exploreUiState.categoryData,
                imageRes = currentImageResource,
                onCategoryClick = { category ->
                    exploreViewmodel.updateCurrentCategory(category)
                    exploreViewmodel.updateCurrentFunMenu(null)
                    navController.navigate(ExpandedMenu.route)
                }
            )
        }
        composable(route = ExpandedMenu.route) {
            ExpandedMenuScreen(
                funMenu = exploreUiState.currentCategory?.categoryList
                    ?: emptyList(),
                onMenuClick = { funMenu ->
                    exploreViewmodel.updateCurrentFunMenu(funMenu)
                },
                onFavoriteClick = {updatedFunMenu ->
                    exploreViewmodel.updateFunMenu(updatedFunMenu)
                },
                selectedCategory = exploreUiState.currentCategory,
                selectedMenu = exploreUiState.currentFunMenu
            )
        }
    }
}


fun NavHostController.navigateSingleTopTo(route: String) =
    this.navigate(route) {
        if (currentDestination?.route != route) {
            popUpTo(
                this@navigateSingleTopTo.graph.findStartDestination().id
            ) {
                saveState = true
            }
        }
        launchSingleTop = true
        restoreState = true
    }

