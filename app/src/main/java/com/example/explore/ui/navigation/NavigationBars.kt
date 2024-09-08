package com.example.explore.ui.navigation

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBackIos
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.NavigationRailItemDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.explore.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(
    modifier: Modifier = Modifier,
    titleResourceId: Int? = null,
    defaultTitle: String,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
) {
    CenterAlignedTopAppBar(
        title = {
            if (titleResourceId != null) {
                Text(stringResource(titleResourceId))
            } else {
                Text(defaultTitle)
            }
        },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBackIos,
                        contentDescription = stringResource(R.string.back_button),
                        modifier = Modifier
                            .size(25.dp)
                            .animateContentSize()
                    )
                }
            }
        }
    )
}


@Composable
fun BottomAppBar(
    currentScreen: String,
    onHomeClick: () -> Unit,
    onFavoritesClick: () -> Unit,
    modifier: Modifier = Modifier,
) {

    NavigationBar(
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        modifier = modifier
    ) {
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector =
                        if (
                            currentScreen in setOf(
                                Category.route,
                                Menu.route,
                                NomDetail.route,
                            )
                        ) Icons.Default.Home
                        else Icons.Outlined.Home,
                    contentDescription = null
                )
            },
            label = {
                Text(
                    text = stringResource(R.string.bottom_navigation_home),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.inverseSurface,
                )
            },
            colors = NavigationBarItemDefaults.colors(
                indicatorColor = Color.Transparent,
            ),
            onClick =  { onHomeClick() },
            selected = currentScreen in setOf(
                Category.route,
                Menu.route,
                NomDetail.route
            ),
            modifier = modifier.animateContentSize()
        )

        NavigationBarItem(
            icon = {
                Icon(
                    imageVector =
                    if (
                        currentScreen in setOf(
                            Category.route,
                            Menu.route,
                            NomDetail.route
                        )
                    ) Icons.Outlined.FavoriteBorder
                    else Icons.Outlined.Favorite,
                    contentDescription = null
                )
            },
            label = {
                Text(
                    text = stringResource(R.string.bottom_navigation_favorite),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.inverseSurface,
                )
            },
            colors = NavigationBarItemDefaults.colors(
                indicatorColor = Color.Transparent,
            ),
            selected = currentScreen in setOf(
                Favorite.route,
                ExpDetail.route
            ),
            onClick = { onFavoritesClick() },
            modifier = modifier.animateContentSize()

        )
    }
}


@Composable
fun Rail(
    currentScreen: String,
    onHomeClick: () -> Unit,
    onFavoritesClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    NavigationRail(
        containerColor = MaterialTheme.colorScheme.primaryContainer,
    ) {
        Column(
            modifier = modifier,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            NavigationRailItem(
                icon = {
                    Icon(
                        imageVector =
                        if (
                            currentScreen in setOf(
                                ExpandedCategory.route,
                                ExpandedMenu.route
                            )
                        ) Icons.Default.Home
                        else Icons.Outlined.Home,
                        contentDescription = null,
                        modifier = Modifier.size(34.dp)

                    )
                },
                label = {
                    Text(
                        text = stringResource(R.string.bottom_navigation_home),
                        style = MaterialTheme.typography.displayMedium,
                        color = MaterialTheme.colorScheme.inverseSurface,
                    )
                },
                colors = NavigationRailItemDefaults.colors(
                    indicatorColor = Color.Transparent,
                ),
                onClick = {onHomeClick()},
                selected = currentScreen in setOf(
                    ExpandedCategory.route,
                    ExpandedMenu.route
                )
            )
            Spacer(modifier = Modifier.height(50.dp))
            NavigationRailItem(
                icon = {
                    Icon(
                        imageVector =
                            if (
                                currentScreen in setOf(
                                    ExpandedCategory.route,
                                    ExpandedMenu.route
                                )
                            ) Icons.Outlined.FavoriteBorder
                            else Icons.Outlined.Favorite,
                            contentDescription = null,
                        modifier = Modifier.size(34.dp)
                    )
                },
                label = {
                    Text(
                        text = stringResource(R.string.bottom_navigation_favorite),
                        style = MaterialTheme.typography.displayMedium,
                        color = MaterialTheme.colorScheme.inverseSurface,
                    )
                },
                colors = NavigationRailItemDefaults.colors(
                    indicatorColor = Color.Transparent,
                ),
                selected = currentScreen == Favorite.route,
                onClick = { onFavoritesClick()}
            )
        }
    }
}