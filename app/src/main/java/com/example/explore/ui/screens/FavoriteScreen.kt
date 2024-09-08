package com.example.explore.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.explore.R
import com.example.explore.ui.viewModel.ExploreViewmodel


@Composable
fun FavoriteScreen(
    onItemClick: (funMenu: com.example.explore.data.FunMenu, category: com.example.explore.data.Category) -> Unit,
    onFavoriteClick: (funMenu: com.example.explore.data.FunMenu, category: com.example.explore.data.Category) -> Unit,
    viewModel: ExploreViewmodel,
    modifier: Modifier = Modifier,
) {
    val favoriteFunMenus by viewModel.favoriteFunMenus.collectAsState()
    val categoryData by viewModel.exploreUiState.collectAsState()
    val groupedFavorites = favoriteFunMenus.groupBy { it.categoryId }

    if (favoriteFunMenus.isEmpty()) {
        Text(
            text = "No favorites yet!",
            modifier = modifier.padding(16.dp)
        )
    } else {
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background),
            contentPadding = PaddingValues(
                vertical = dimensionResource(R.dimen.padding_large),
            ),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_large)),
        ) {
            groupedFavorites.forEach { (categoryId, funMenus) ->
                val category = categoryData.categoryData.find { it.id == categoryId }
                item {
                    Text(
                        text = category?.titleResourceId?.let { stringResource(id = it) } ?: "",
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                    LazyRow(
                        modifier = modifier.fillMaxSize(),
                        horizontalArrangement = Arrangement.spacedBy(25.dp),
                        contentPadding = PaddingValues(
                            vertical = dimensionResource(R.dimen.padding_large),
                            horizontal = dimensionResource(R.dimen.padding_large)
                        ),
                    ) {
                        funMenus.forEach { funMenuKey ->
                            val funMenu = category?.categoryList?.find { it.id == funMenuKey.funMenuId }
                            item {
                                funMenu?.let {
                                   FavoriteMenuCard(
                                       imageRes = it.imageResourceId,
                                       titleRes = it.titleResourceId,
                                       icon = androidx.compose.material.icons.Icons.Filled.Favorite,
                                       iconContentDescription = R.string.add_favorite,
                                       onItemClick = { onItemClick( funMenu, category) },
                                       onFavoriteClick = { onFavoriteClick( funMenu, category)}
                                   )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun FavoriteMenuCard(
    imageRes: Int,
    titleRes: Int,
    icon: ImageVector,
    iconContentDescription: Int,
    onItemClick: () -> Unit,
    onFavoriteClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        elevation = CardDefaults.cardElevation(),
        shape = RoundedCornerShape(dimensionResource(R.dimen.card_corner_radius)),
        colors = CardDefaults.cardColors(
               containerColor = MaterialTheme.colorScheme.surfaceVariant,
               contentColor = MaterialTheme.colorScheme.inverseSurface
        ),
           onClick = onItemClick
       ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = modifier
        ) {
            Image(
                painter = painterResource(imageRes),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = modifier
            )
            Text(
                style = MaterialTheme.typography.bodyLarge,
                text = stringResource(titleRes),
                modifier = modifier
                    .paddingFromBaseline(
                        top = 24.dp,
                        bottom = 8.dp
                    )
            )
            IconButton(
                onClick = onFavoriteClick,
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = stringResource(iconContentDescription),
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = modifier.size(26.dp)
                )
            }
        }
    }
}
