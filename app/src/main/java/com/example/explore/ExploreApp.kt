package com.example.explore

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.explore.ui.screen.ExploreScreen


@Composable
fun ExploreApp(){
    val navController = rememberNavController()
    ExploreScreen(navController = navController)
}