package com.example.explore.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.explore.R
import com.example.explore.model.Category
import com.example.explore.model.FunMenu
import com.example.explore.ui.viewModel.ExploreViewmodel


@Composable
fun FavoriteScreen(
    onItemClick: (funMenu: FunMenu, category: Category) -> Unit,
    onFavoriteClick: (funMenu: FunMenu, category: Category) -> Unit,
    viewModel: ExploreViewmodel,
    modifier: Modifier = Modifier,
) {
    val favoriteFunMenus by viewModel.favoriteFunMenus.collectAsState()
    val categoryData by viewModel.exploreUiState.collectAsState()
    val groupedFavorites = favoriteFunMenus.groupBy { it.categoryId }

    if (favoriteFunMenus.isEmpty()) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = modifier
        ){
            Text(
                text = stringResource(R.string.no_favorites),
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary,
                modifier = modifier
            )
        }
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
                        color = MaterialTheme.colorScheme.primary,
                        modifier = modifier
                            .padding(start = 10.dp, end = 10.dp)
                            .clip(RoundedCornerShape(dimensionResource(R.dimen.card_corner_radius)))
                            .background(color = MaterialTheme.colorScheme.surfaceVariant)
                            .fillMaxWidth()
                            .padding(start = 20.dp, top = 10.dp, bottom = 10.dp)
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
            verticalArrangement = Arrangement.Top,
            modifier = modifier.size(300.dp)
        ) {
            Image(
                painter = painterResource(imageRes),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .fillMaxWidth()
                    .size(200.dp)
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
                    modifier = modifier
                        .fillMaxHeight()
                        .size(26.dp)

                )
            }
        }
    }
}
