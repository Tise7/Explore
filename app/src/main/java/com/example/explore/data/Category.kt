package com.example.explore.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes


data class Category(
    val id: Int,
    @StringRes val titleResourceId: Int,
    @StringRes val descriptionResourceId: Int ,
    @DrawableRes val imageResourceId: Int,
    val categoryList: List<FunMenu>
)





