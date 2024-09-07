package com.example.explore.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.room.Entity
import androidx.room.PrimaryKey

data class FunMenu(
    val id: Long,
    @StringRes val titleResourceId: Int,
    @StringRes val descriptionResourceId: Int,
    @DrawableRes val imageResourceId: Int,
    var isFavorite: Boolean = false
)