package com.example.explore.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class FunMenu(
    val id: Long,
    @StringRes val titleResourceId: Int,
    @StringRes val descriptionResourceId: Int,
    @DrawableRes val imageResourceId: Int,
)