package com.example.explore.data

import com.example.explore.R


data class ImageResData(val resources: List<Int>)
object AppImageResources {
    val images = ImageResData(
        listOf(
            R.drawable.one,
            R.drawable.two,
            R.drawable.three,
            R.drawable.four,
            R.drawable.five,
            R.drawable.six,
            R.drawable.seven,
            R.drawable.eight,
        )
    )
}