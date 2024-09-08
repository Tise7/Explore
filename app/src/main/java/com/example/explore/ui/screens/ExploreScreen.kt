package com.example.explore.ui.screens

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.explore.R
import com.example.explore.ui.screenOrientation.rememberDeviceTypeHelper
import com.example.explore.ui.navigation.BottomAppBar
import com.example.explore.ui.navigation.Category
import com.example.explore.ui.navigation.ExpDetail
import com.example.explore.ui.navigation.NomDetail
import com.example.explore.ui.navigation.ExpandedCategory
import com.example.explore.ui.navigation.ExpandedMenu
import com.example.explore.ui.navigation.ExploreNavigation
import com.example.explore.ui.navigation.Favorite
import com.example.explore.ui.navigation.Menu
import com.example.explore.ui.navigation.Rail
import com.example.explore.ui.navigation.Screens
import com.example.explore.ui.navigation.TopAppBar
import com.example.explore.ui.navigation.Welcome
import com.example.explore.ui.navigation.navigateSingleTopTo
import com.example.explore.ui.viewModel.AppViewModelProvider
import com.example.explore.ui.viewModel.ExploreViewmodel


@Composable
fun ExploreScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    exploreViewmodel: ExploreViewmodel = viewModel(factory = AppViewModelProvider.Factory),
){
    val uiState by exploreViewmodel.exploreUiState.collectAsState()
    val selectedCategoryTitleResourceId = uiState.currentCategory?.titleResourceId

    val deviceTypeHelper = rememberDeviceTypeHelper()
    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE || configuration.screenWidthDp > 600


    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = backStackEntry?.destination
    val currentScreen = Screens.find{ it.route == currentDestination?.route } ?: Welcome
    val canNavigateBack = navController.previousBackStackEntry != null && currentScreen != Category && currentScreen != Favorite && currentScreen != ExpandedCategory


    Scaffold(
        topBar = {
            if ( currentScreen != Welcome) {
                when (currentScreen) {
                    Category -> {
                        TopAppBar(
                            defaultTitle = stringResource(R.string.category),
                            canNavigateBack = canNavigateBack,
                            navigateUp = { navController.navigateUp() }
                        )
                    }
                    Menu -> {
                        TopAppBar(
                            titleResourceId = selectedCategoryTitleResourceId,
                            defaultTitle = "",
                            canNavigateBack = canNavigateBack,
                            navigateUp = { navController.navigateUp() }
                        )
                    }
                    NomDetail -> {
                        TopAppBar(
                            defaultTitle = "",
                            canNavigateBack = canNavigateBack,
                            navigateUp = { navController.navigateUp() }
                        )
                    }
                    ExpDetail -> {
                        TopAppBar(
                            defaultTitle = "",
//                            titleResourceId = R.string.bottom_navigation_favorite,
                            canNavigateBack = canNavigateBack,
                            navigateUp = { navController.navigateUp() }
                        )
                    }
                    Favorite -> {
                        TopAppBar(
                            defaultTitle = "",
                            titleResourceId = R.string.favorite,
                            canNavigateBack = canNavigateBack,
                            navigateUp = { navController.navigateUp() }
                        )
                    }
                    ExpandedCategory -> {
                        TopAppBar(
                            defaultTitle = "",
                            canNavigateBack = canNavigateBack,
                            navigateUp = { navController.navigateUp() }
                        )
                    }
                    ExpandedMenu -> {
                        TopAppBar(
                            defaultTitle = "",
                            titleResourceId = selectedCategoryTitleResourceId,
                            canNavigateBack = canNavigateBack,
                            navigateUp = { navController.navigateUp() }
                        )
                    }
                }
            }
        },
        bottomBar = {
            if (!isLandscape && currentScreen != Welcome) {
                BottomAppBar(
                    currentScreen = currentScreen.route,
                    onHomeClick = {
                        navController.navigateSingleTopTo(Category.route)
                    },
                    onFavoritesClick = {
                        navController.navigateSingleTopTo(Favorite.route)
                    },
                    modifier = modifier,
                )
            }
        }
    ) {innerPadding ->
        Row {
            if (isLandscape && currentScreen != Welcome) {
                Rail(
                    currentScreen = currentScreen.route,
                    onHomeClick = {
                        navController.navigateSingleTopTo(ExpandedCategory.route)
                    },
                    onFavoritesClick = {
                        navController.navigateSingleTopTo(Favorite.route)
                    },
                    modifier = modifier
                        .padding(innerPadding)
                        .weight(.1f)
                )
            }
            Column(modifier = modifier.weight(if (isLandscape) 0.9f else 1f)) {
                ExploreNavigation(
                    exploreUiState = uiState,
                    exploreViewmodel = exploreViewmodel,
                    navController = navController,
                    deviceTypeHelper = deviceTypeHelper,
                    modifier = modifier.padding(innerPadding)
                )
            }
        }
    }
}


