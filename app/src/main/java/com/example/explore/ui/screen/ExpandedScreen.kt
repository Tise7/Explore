package com.example.explore.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.explore.data.Category
import com.example.explore.data.FunMenu
import com.example.explore.ui.reuseables.RandomImageResource


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
                imageRes = imageRes
            )
        }
    }
}

@Composable
fun ExpandedMenuScreen(
    funMenu: List<FunMenu>,
    onMenuClick: (FunMenu) -> Unit,
    selectedCategory: Category?,
    selectedMenu: FunMenu?,
    onFavoriteClick: (FunMenu) -> Unit,
    modifier: Modifier = Modifier
){
    Row(
        modifier = modifier
            .fillMaxWidth()
    ){
        Column(modifier = modifier.weight(1.2f)) {
            FunMenuList(
                funMenu = funMenu,
                onFavoriteClick = { updatedFunMenu ->
                    onFavoriteClick(updatedFunMenu)
                },
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
