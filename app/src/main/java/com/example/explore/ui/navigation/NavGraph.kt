package com.example.explore.ui.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.explore.ui.screenOrientation.DeviceTypeHelper
import com.example.explore.ui.screens.CategoryList
import com.example.explore.ui.screens.ExpandedCategoryList
import com.example.explore.ui.screens.ExpandedDetailScreen
import com.example.explore.ui.screens.ExpandedMenuScreen
import com.example.explore.ui.screens.FavoriteScreen
import com.example.explore.ui.screens.FunMenuList
import com.example.explore.ui.screens.WelcomeScreen
import com.example.explore.ui.viewModel.ExploreUiState
import com.example.explore.ui.viewModel.ExploreViewmodel


@Composable
fun ExploreNavigation(
    exploreUiState: ExploreUiState,
    exploreViewmodel: ExploreViewmodel,
    deviceTypeHelper: DeviceTypeHelper,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val currentImageResource by exploreViewmodel.currentImageResource.collectAsState()
    val favoriteFunMenus by exploreViewmodel.favoriteFunMenus.collectAsState()

    val isLandscapeAndTablet = deviceTypeHelper.isLandscape() || deviceTypeHelper.isTablet()

    NavHost(
        navController = navController,
        startDestination = Welcome.route,
        modifier = modifier.fillMaxSize()
    ) {
        composable(route = Welcome.route) {
            WelcomeScreen(
                onNavigateToMenuList = {
                    val nextRoute = if (isLandscapeAndTablet) ExpandedCategory.route else Category.route
                    navController.navigateSingleTopTo(nextRoute)
                }
            )
        }
        composable(route = Favorite.route) {
            exploreUiState.currentCategory?.let { _ ->
                FavoriteScreen(
                    onItemClick = { funMenu, _ ->
                        exploreViewmodel.updateCurrentFunMenu( funMenu)
                        navController.navigate(ExpDetail.route)
                    },
                    onFavoriteClick = {updatedFunMenu, it ->
                        exploreViewmodel.updateFavoriteFunMenu(it, updatedFunMenu)
                    },
                    viewModel = exploreViewmodel
                )
            }
        }

        composable(route = Category.route) {
            CategoryList(
                categories = exploreUiState.categoryData,
                onItemClick = { category ->
                    exploreViewmodel.updateCurrentCategory(category)
                    val nextRoute = if (isLandscapeAndTablet) ExpandedMenu.route else Menu.route
                    navController.navigate(nextRoute)
                }
            )
        }
        composable(route = Menu.route) {
            exploreUiState.currentCategory?.let { it1 ->
                FunMenuList(
                    category = it1,
                    onFavoriteClick = { it, updatedFunMenu ->
                        exploreViewmodel.updateFavoriteFunMenu(it, updatedFunMenu)
                    },
                    favoriteFunMenus = favoriteFunMenus,
                    onItemClick = { funMenu ->
                        exploreViewmodel.updateCurrentFunMenu(funMenu)
                        navController.navigate(NomDetail.route)
                    }
                )
            }
        }
        composable(route = NomDetail.route) {
            ExpandedDetailScreen(
                imageRes = exploreUiState.currentFunMenu?.imageResourceId ?: 0,
                titleRes = exploreUiState.currentFunMenu?.titleResourceId ?: 0,
                descriptionRes = exploreUiState.currentFunMenu?.descriptionResourceId ?: 0
            )
        }

        composable(route = ExpDetail.route) {
            ExpandedDetailScreen(
                imageRes = exploreUiState.currentFunMenu?.imageResourceId ?: 0,
                titleRes = exploreUiState.currentFunMenu?.titleResourceId ?: 0,
                descriptionRes = exploreUiState.currentFunMenu?.descriptionResourceId ?: 0
            )
        }
        composable(route = ExpandedCategory.route) {
            val onCategoryClickRemembered = remember {
                { category: com.example.explore.data.Category ->
                    exploreViewmodel.updateCurrentCategory(category)
                    exploreViewmodel.updateCurrentFunMenu(null)
                    navController.navigate(ExpandedMenu.route)
                }
            }
            ExpandedCategoryList(
                categories = exploreUiState.categoryData,
                imageRes = currentImageResource,
                onCategoryClick = onCategoryClickRemembered
            )
//            ExpandedCategoryList(
//                categories = exploreUiState.categoryData,
//                imageRes = currentImageResource,
//                onCategoryClick = { category ->
//                    exploreViewmodel.updateCurrentCategory(category)
//                    exploreViewmodel.updateCurrentFunMenu(null)
//                    navController.navigate(ExpandedMenu.route)
//                }
//            )
        }
        composable(route = ExpandedMenu.route) {
            exploreUiState.currentCategory?.let { category ->
                ExpandedMenuScreen(
                    onMenuClick = { funMenu ->
                        exploreViewmodel.updateCurrentFunMenu(funMenu)
                    },
                    category = category,
                    selectedMenu = exploreUiState.currentFunMenu,
                    onFavoriteClick = {it, updatedFunMenu ->
                        exploreViewmodel.updateFavoriteFunMenu( it, updatedFunMenu)
                    },
                    favoriteFunMenus = favoriteFunMenus,
                    selectedCategory = exploreUiState.currentCategory,

                )
            }
        }
    }
}


fun NavHostController.navigateSingleTopTo(route: String) =
    this.navigate(route) {
//        if (currentDestination?.route != route) {
            popUpTo(
                this@navigateSingleTopTo.graph.findStartDestination().id
            ) {
                saveState = true
            }
//        }
        launchSingleTop = true
        restoreState = true
    }

