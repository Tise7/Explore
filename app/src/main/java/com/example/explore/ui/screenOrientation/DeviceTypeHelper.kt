package com.example.explore.ui.screenOrientation

import android.content.Context
import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext


class DeviceTypeHelper(private val context: Context) {

    fun isTablet(): Boolean {
        val configuration = context.resources.configuration
        val screenSize = configuration.screenLayout and Configuration.SCREENLAYOUT_SIZE_MASK
        return screenSize == Configuration.SCREENLAYOUT_SIZE_LARGE || screenSize == Configuration.SCREENLAYOUT_SIZE_XLARGE
    }

    fun isLandscape(): Boolean {
        val configuration = context.resources.configuration
        return configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
    }
}


@Composable
fun rememberDeviceTypeHelper(): DeviceTypeHelper {
    val context = LocalContext.current
    return remember { DeviceTypeHelper(context) }
}

