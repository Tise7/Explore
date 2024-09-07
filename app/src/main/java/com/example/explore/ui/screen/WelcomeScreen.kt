package com.example.explore.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.explore.R


@Composable
fun WelcomeScreen (
    onNavigateToMenuList: () -> Unit
) {
    Box (
        modifier = Modifier.fillMaxSize()
    ) {
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxSize()
                .padding(dimensionResource(R.dimen.font_size_large))
        ) {
            Text(
                stringResource(id = R.string.welcome_text),
                style = MaterialTheme.typography.displayLarge,
                color = MaterialTheme.colorScheme.primary,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .clickable {
                        onNavigateToMenuList()
                    }
            )
            FilledTonalButton(
                onClick = onNavigateToMenuList,
                modifier = Modifier
                    .width(130.dp)
                    .height(50.dp)
            ) {
                Text(
                    text = "Explore",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.inverseSurface,
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun WelcomePreview () {
    WelcomeScreen(
        onNavigateToMenuList = {}
    )
}


