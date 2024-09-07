package com.example.explore.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.example.explore.R
import com.example.explore.data.FunMenu
import com.example.explore.ui.reuseables.ImageResource



@Composable
fun FunMenuCard (
    imageRes: Int,
    titleRes: Int,
    icon: ImageVector,
    iconContentDescription: Int,
    onItemClick: () -> Unit,
    onFavoriteClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    Box(modifier = modifier) {
        Card(
            elevation = CardDefaults.cardElevation(),
            shape = RoundedCornerShape(dimensionResource(R.dimen.card_corner_radius)),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant,
                contentColor = MaterialTheme.colorScheme.inverseSurface,
            ),
            onClick = onItemClick
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .size(dimensionResource(R.dimen.card_image_height))
            ) {
                ImageResource(
                    imageRes = imageRes,
                    modifier = modifier
                        .weight(1f)
                )
                Text(
                    text = stringResource(titleRes),
                    style = MaterialTheme.typography.titleMedium,
                    modifier = modifier
                        .padding(horizontal = dimensionResource(R.dimen.padding_medium))
                        .padding(bottom = dimensionResource(R.dimen.padding_large))
                        .weight(1.5f)
                )
                IconButton(
                    modifier = modifier.align(Alignment.CenterVertically),
                    onClick = onFavoriteClick,
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = stringResource(iconContentDescription)
                    )
                }
            }
        }
    }
}


@Composable
fun FunMenuList (
    funMenu: List<FunMenu>,
    onItemClick:(FunMenu) -> Unit,
    onFavoriteClick: (FunMenu) -> Unit,
    modifier: Modifier = Modifier
) {
    val rememberedCategories = rememberSaveable { funMenu }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentPadding = PaddingValues(
            vertical = dimensionResource(R.dimen.padding_large),
            horizontal = dimensionResource(R.dimen.padding_large)
        ),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_large)),
    ) {
        items(items = rememberedCategories, key = { funMenu -> funMenu.id }) { funMenu ->
            val isFavorite = remember { mutableStateOf(funMenu.isFavorite) }
            FunMenuCard(
                imageRes = funMenu.imageResourceId,
                titleRes = funMenu.titleResourceId,
                onItemClick  = { onItemClick(funMenu) },
                icon = if (isFavorite.value) Icons.Filled.Favorite else Icons.Default.FavoriteBorder,
                iconContentDescription = if (isFavorite.value) R.string.remove_favorite else R.string.add_favorite,
                onFavoriteClick = {
                    isFavorite.value = !isFavorite.value
                    onFavoriteClick(funMenu.copy(isFavorite = isFavorite.value))
                }
            )
        }
    }
}