package com.example.explore.ui.screens

import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.rememberTransition
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.MenuOpen
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.explore.R
import com.example.explore.ui.theme.FunEUTheme

@Composable
fun NormalDetailScreen(
    imageRes: Int,
    titleRes: Int,
    descriptionRes: Int,
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Box {
            Image(
                painter = painterResource(imageRes),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .fillMaxWidth()
                    .height(450.dp)
            )
            Card (
                elevation = CardDefaults.cardElevation(),
                shape = RoundedCornerShape( topStart = 70.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceDim
                ),
                modifier = modifier
                    .fillMaxHeight()
                    .padding(top = 390.dp)
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = modifier.fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(titleRes),
                        style = MaterialTheme.typography.titleLarge,
                        textAlign = TextAlign.Start,
                        color = MaterialTheme.colorScheme.inverseSurface,
                        modifier = modifier
                            .padding(
                                horizontal = 35.dp,
                                vertical = 15.dp
                            )
                    )
                    Text(
                        text = stringResource(descriptionRes),
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onTertiaryContainer,
                        modifier = modifier
                            .padding(top = 15.dp)
                            .padding(horizontal = dimensionResource(R.dimen.padding_detail_content_horizontal))
                    )
                }
            }
        }
    }
}


@Composable
fun ExpandedDetailScreen(
    imageRes: Int,
    titleRes: Int,
    descriptionRes: Int,
    modifier: Modifier = Modifier
){
    var expanded by remember{ mutableStateOf(false) }
    val transitionState = remember {
        MutableTransitionState(expanded).apply {
            targetState = !expanded
        }
    }
    val transition = rememberTransition(transitionState, label = "expand")

    val cardOffset by transition.animateDp(
        transitionSpec = {
            if (targetState) {
                spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessVeryLow
                )
            } else {
                spring(
                    dampingRatio = Spring.DampingRatioHighBouncy, // Increased damping
                    stiffness = Spring.StiffnessLow
                )
            }
        },
        label = "cardHeight"
    ) { if (it) 0.dp else 1200.dp }

    Box(modifier = modifier) {
        Column(modifier = modifier) {
            Box{
                Image(
                    painter = painterResource(imageRes),
                    contentDescription = null,
                    alignment = Alignment.Center,
                    contentScale = ContentScale.Crop,
                    modifier = modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                )
                FloatingActionButton(
                    onClick = {
                        expanded = !expanded
                        transitionState.targetState = !expanded
                    },
                    containerColor = if (expanded)  MaterialTheme.colorScheme.surfaceDim else MaterialTheme.colorScheme.inverseSurface,
                    contentColor = if (expanded)  MaterialTheme.colorScheme.inverseSurface else MaterialTheme.colorScheme.surfaceDim,
                    modifier = modifier
                        .align(Alignment.TopEnd)
                        .padding(top = 16.dp, bottom = 16.dp, end = 50.dp)

                ) {
                    Icon(
                        imageVector = if (expanded) Icons.AutoMirrored.Filled.MenuOpen else Icons.Filled.Close,
                        contentDescription = if (expanded) "Collapse" else "Expand",
                    )
                }
                Box (modifier = modifier
                    .offset(x = cardOffset)
                    .fillMaxHeight()
                ) {
                    Column(
                        modifier = modifier
                            .fillMaxHeight(),
                    ) {
                        Card (
                            elevation = CardDefaults.cardElevation(),
                            shape = RoundedCornerShape( topStart = 70.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.surfaceDim,
                                contentColor = MaterialTheme.colorScheme.inverseSurface,
                            ),
                            modifier = modifier
                                .clickable {
                                    expanded = !expanded
                                    transitionState.targetState = !expanded
                                }
                                .padding(top = 80.dp)
                                .fillMaxHeight()
                        ) {

                            Column(
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = modifier
                                    .verticalScroll(rememberScrollState())
                                    .fillMaxWidth()
                            ) {
                                Text(
                                    text = stringResource(titleRes),
                                    style = MaterialTheme.typography.titleLarge,
                                    textAlign = TextAlign.Start,
                                    modifier = modifier
                                        .padding(
                                            horizontal = 35.dp,
                                            vertical = 15.dp
                                        )
                                )
                                Text(
                                    text = stringResource(descriptionRes),
                                    style = MaterialTheme.typography.bodySmall,
                                    modifier = modifier
                                        .padding(horizontal = 70.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun DetailScreenPreview() {
    FunEUTheme {
        val funMenu = com.example.explore.data.FunMenu(
            id = 1,
            titleResourceId = R.string.coffee_shops_2,
            descriptionResourceId = R.string.coffee_shops_2_description,
            imageResourceId = R.drawable.coffee_shops_1
        )
        NormalDetailScreen(
            imageRes = funMenu.imageResourceId,
            titleRes = funMenu.titleResourceId,
            descriptionRes = funMenu.descriptionResourceId
        )
    }
}

