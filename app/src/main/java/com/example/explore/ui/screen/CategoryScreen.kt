package com.example.explore.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.explore.R
import com.example.explore.data.Category
import com.example.explore.data.FunListDataProvider
import com.example.explore.ui.reuseables.ImageResource
import com.example.explore.ui.theme.FunEUTheme

@Composable
fun CategoryCard(
    modifier: Modifier = Modifier,
    onItemClick: () -> Unit,
    imageRes: Int,
    titleRes: Int,
    descriptionRes: Int
) {
    Box(
        modifier = modifier
            .height(290.dp)
            .clip(RoundedCornerShape(dimensionResource(R.dimen.card2_corner_radius)))
            .clickable(onClick = onItemClick)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.35f)
                .align(Alignment.TopCenter)
                .background(MaterialTheme.colorScheme.inverseSurface)
        )
        Box(
            modifier = modifier
                .fillMaxWidth()
                .fillMaxHeight(0.65f)
                .align(Alignment.BottomCenter)
                .background(MaterialTheme.colorScheme.surfaceVariant)
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .fillMaxWidth()
                .padding(
                    vertical = dimensionResource(R.dimen.padding_small),
                    horizontal = dimensionResource(R.dimen.padding_medium)
                )
        ) {
            ImageResource(
                imageRes = imageRes,
                modifier = modifier
                    .padding(top = dimensionResource(R.dimen.padding_medium))
                    .clip(shape = CircleShape)
                    .background(Color.Transparent)
                    .size(130.dp)
            )
            Text(
                text = stringResource(titleRes),
                style = MaterialTheme.typography.titleLarge,
                modifier = modifier
                    .padding(bottom = dimensionResource(R.dimen.card_text_vertical_space))
                    .align(Alignment.Start)
            )
            Text(
                text = stringResource(descriptionRes),
                style = MaterialTheme.typography.bodyLarge,
                overflow = TextOverflow.Ellipsis,
                modifier = modifier
                    .align(Alignment.CenterHorizontally)
            )
        }
    }
}


@Composable
fun CategoryList (
    modifier: Modifier = Modifier,
    categories: List<Category>,
    onItemClick: (Category) -> Unit
) {
    val rememberedCategories = rememberSaveable { categories }

    LazyVerticalGrid(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentPadding = PaddingValues(
            vertical = dimensionResource(R.dimen.padding_large),
            horizontal = dimensionResource(R.dimen.padding_large)
        ),
        horizontalArrangement = Arrangement.spacedBy(
            dimensionResource(R.dimen.padding_large)
        ),
        verticalArrangement = Arrangement.spacedBy(
            dimensionResource(R.dimen.padding_large)
        ),
        columns = androidx.compose.foundation.lazy.grid.GridCells.Fixed(2),

    ) {
        items( items = rememberedCategories, key = { category -> category.id }) { category ->
            CategoryCard(
                onItemClick = { onItemClick(category) },
                imageRes = category.imageResourceId,
                titleRes = category.titleResourceId,
                descriptionRes = category.descriptionResourceId
            )
        }
    }
}

@Preview( showBackground = true)
@Composable
fun CategoryListPreview() {
    FunEUTheme {
        CategoryList(
            categories = FunListDataProvider.categoryList,
            onItemClick = {}
        )
    }
}

@Preview
@Composable
fun CategoryCardPreview() {
    FunEUTheme {
        val category = Category(
            id = 1,
            titleResourceId = R.string.coffee_shops,
            descriptionResourceId = R.string.coffee_shops_description,
            imageResourceId = R.drawable.coffee_shops,
            categoryList = emptyList()
        )
        CategoryCard(
            onItemClick = {},
            imageRes = category.imageResourceId,
            titleRes = category.titleResourceId,
            descriptionRes = category.descriptionResourceId
        )
    }
}