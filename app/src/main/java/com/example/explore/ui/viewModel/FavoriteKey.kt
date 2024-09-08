package com.example.explore.ui.viewModel

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class FavoriteKey(
    val categoryId: Int,
    val funMenuId: Long
): Parcelable