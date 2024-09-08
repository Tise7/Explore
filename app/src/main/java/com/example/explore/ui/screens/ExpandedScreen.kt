package com.example.explore.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.explore.data.Category
import com.example.explore.data.FunMenu
import com.example.explore.ui.reuseables.RandomImageResource
import com.example.explore.ui.viewModel.FavoriteKey


@Composable
fun ExpandedCategoryList(
    imageRes: Int,
    categories: List<Category>,
    onCategoryClick: (Category) -> Unit,
    modifier: Modifier = Modifier
){
    Row(
        modifier = modifier
            .fillMaxWidth()
    ){
        Column(modifier = modifier.weight(0.5f)) {
            CategoryList(
                categories = categories,
                onItemClick = onCategoryClick
            )
        }
        Column(modifier = modifier.weight(0.5f)) {
            RandomImageResource(
                imageRes = imageRes,
                modifier = modifier
                    .fillMaxHeight()
            )
        }
    }
}

@Composable
fun ExpandedMenuScreen(
    category: Category,
    onMenuClick: (FunMenu) -> Unit,
    selectedCategory: Category?,
    selectedMenu: FunMenu?,
    onFavoriteClick: (Category, FunMenu) -> Unit,
    favoriteFunMenus: Set<FavoriteKey>,
    modifier: Modifier = Modifier
){
    Row(
        modifier = modifier
            .fillMaxWidth()
    ){
        Column(modifier = modifier.weight(1.2f)) {
            FunMenuList(
//                funMenu = funMenu,
                onFavoriteClick = {category, updatedFunMenu ->
                    onFavoriteClick(category, updatedFunMenu)
                },
                favoriteFunMenus = favoriteFunMenus,
                category = category,
                onItemClick = { funMenu -> onMenuClick(funMenu)}
            )
        }

        Column(modifier = modifier
            .weight(1.5f)
        ) {
            if (selectedMenu == null) {
                selectedCategory?.let { category ->
                    Image(
                        painter = painterResource( category.imageResourceId),
                        contentScale = ContentScale.Crop,
                        contentDescription = null,
                        modifier = modifier.fillMaxSize()
                    )
                }
            } else {
                selectedMenu.let { menu ->
                    ExpandedDetailScreen(
                        imageRes = menu.imageResourceId,
                        titleRes = menu.titleResourceId,
                        descriptionRes = menu.descriptionResourceId
                    )
                }
            }
        }
    }
}
