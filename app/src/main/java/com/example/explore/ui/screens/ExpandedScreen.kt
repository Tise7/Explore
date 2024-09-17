package com.example.explore.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.explore.model.Category
import com.example.explore.model.FunMenu
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
                onFavoriteClick = {category, updatedFunMenu ->
                    onFavoriteClick(category, updatedFunMenu)
                },
                favoriteFunMenus = favoriteFunMenus,
                category = category,
                onItemClick = { funMenu -> onMenuClick(funMenu)}
            )
        }

        Column(modifier = modifier.weight(1.5f)) {
            if (selectedMenu == null) {
                selectedCategory?.let { category ->
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = modifier
                    ) {
                        Image(
                            painter = painterResource( category.imageResourceId),
                            contentScale = ContentScale.Crop,
                            contentDescription = null,
                            modifier = modifier.fillMaxSize()
                        )
                        Text(
                            text = stringResource(category.titleResourceId),
                            style = MaterialTheme.typography.displayLarge,
                            color = MaterialTheme.colorScheme.inversePrimary,
                            modifier = modifier
                                .align(Alignment.TopCenter)
                                .padding(top = 10.dp)
                        )
                    }

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
