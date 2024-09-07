package com.example.explore.data

import com.example.explore.R


object FunListDataProvider {

    private val CoffeeShops = Category(
        id = 0,
        titleResourceId = R.string.coffee_shops,
        descriptionResourceId = R.string.coffee_shops_description,
        imageResourceId = R.drawable.coffee_shops,
        categoryList = listOf(
            FunMenu(
                id = 1,
                titleResourceId = R.string.coffee_shops_1,
                imageResourceId = R.drawable.coffee_shops_1,
                descriptionResourceId = R.string.coffee_shops_1_description
            ),
            FunMenu(
                id = 2,
                titleResourceId = R.string.coffee_shops_2,
                imageResourceId = R.drawable.coffee_shops_2,
                descriptionResourceId = R.string.coffee_shops_2_description
            ),
            FunMenu(
                id = 3,
                titleResourceId = R.string.coffee_shops_3,
                imageResourceId = R.drawable.coffee_shops_3,
                descriptionResourceId = R.string.coffee_shops_3_description
            ),
            FunMenu(
                id = 4,
                titleResourceId = R.string.coffee_shops_4,
                imageResourceId = R.drawable.coffee_shops_4,
                descriptionResourceId = R.string.coffee_shops_4_description
            ),
            FunMenu(
                id = 5,
                titleResourceId = R.string.coffee_shops_5,
                imageResourceId = R.drawable.coffee_shops_5,
                descriptionResourceId = R.string.coffee_shops_5_description
            ),
            FunMenu(
                id = 6,
                titleResourceId = R.string.coffee_shops_6,
                imageResourceId = R.drawable.coffee_shops_6,
                descriptionResourceId = R.string.coffee_shops_6_description
            ),
            FunMenu(
                id = 7,
                titleResourceId = R.string.coffee_shops_7,
                imageResourceId = R.drawable.coffee_shops_7,
                descriptionResourceId = R.string.coffee_shops_7_description
            ),
            FunMenu(
                id = 8,
                titleResourceId = R.string.coffee_shops_8,
                imageResourceId = R.drawable.coffee_shops_8,
                descriptionResourceId = R.string.coffee_shops_8_description
            )
        )
    )

    private val Restaurants = Category(
        id = 1,
        titleResourceId = R.string.restaurants,
        descriptionResourceId = R.string.restaurants_description,
        imageResourceId = R.drawable.restaurants,
        categoryList = listOf(
            FunMenu(
                id = 1,
                titleResourceId = R.string.restaurants_1,
                imageResourceId = R.drawable.restaurants_1,
                descriptionResourceId = R.string.restaurants_1_description
            ),
            FunMenu(
                id = 2,
                titleResourceId = R.string.restaurants_2,
                imageResourceId = R.drawable.restaurants_2,
                descriptionResourceId = R.string.restaurants_2_description
            ),
            FunMenu(
                id = 3,
                titleResourceId = R.string.restaurants_3,
                imageResourceId = R.drawable.restaurants_3,
                descriptionResourceId = R.string.restaurants_3_description
            ),
            FunMenu(
                id = 4,
                titleResourceId = R.string.restaurants_4,
                imageResourceId = R.drawable.restaurants_4,
                descriptionResourceId = R.string.restaurants_4_description
            ),
            FunMenu(
                id = 5,
                titleResourceId = R.string.restaurants_5,
                imageResourceId = R.drawable.restaurants_5,
                descriptionResourceId = R.string.restaurants_5_description
            ),
            FunMenu(
                id = 6,
                titleResourceId = R.string.restaurants_6,
                imageResourceId = R.drawable.restaurants_6,
                descriptionResourceId = R.string.restaurants_6_description
            ),
            FunMenu(
                id = 7,
                titleResourceId = R.string.restaurants_7,
                imageResourceId = R.drawable.restaurants_7,
                descriptionResourceId = R.string.restaurants_7_description
            )
        )
    )

    private val Gyms = Category(
        id = 2,
        titleResourceId = R.string.gyms,
        descriptionResourceId = R.string.gym_description,
        imageResourceId = R.drawable.gym,
        categoryList = listOf(
            FunMenu(
                id = 1,
                titleResourceId = R.string.gym_1,
                imageResourceId = R.drawable.gym_1,
                descriptionResourceId = R.string.gym_1_description
            ),
            FunMenu(
                id = 2,
                titleResourceId = R.string.gym_2,
                imageResourceId = R.drawable.gym_2,
                descriptionResourceId = R.string.gym_2_description
            ),
            FunMenu(
                id = 3,
                titleResourceId = R.string.gym_3,
                imageResourceId = R.drawable.gym_3,
                descriptionResourceId = R.string.gym_3_description
            ),
            FunMenu(
                id = 4,
                titleResourceId = R.string.gym_4,
                imageResourceId = R.drawable.gym_4,
                descriptionResourceId = R.string.gym_4_description
            ),
            FunMenu(
                id = 5,
                titleResourceId = R.string.gym_5,
                imageResourceId = R.drawable.gym_5,
                descriptionResourceId = R.string.gym_5_description
            ),
        )

    )

    private val AmusementParks = Category(
        id = 3,
        titleResourceId = R.string.amusement_parks,
        descriptionResourceId = R.string.amusement_parks_description,
        imageResourceId = R.drawable.amusement_parks,
        categoryList = listOf(
            FunMenu(
                id = 1,
                titleResourceId = R.string.amusement_Park_1,
                imageResourceId = R.drawable.amusement_park_1,
                descriptionResourceId = R.string.amusement_Park_1_description
            ),
            FunMenu(
                id = 2,
                titleResourceId = R.string.amusement_Park_2,
                imageResourceId = R.drawable.amusement_park_2,
                descriptionResourceId = R.string.amusement_Park_2_description
            ),
            FunMenu (
                id = 3,
                titleResourceId = R.string.amusement_Park_3,
                imageResourceId = R.drawable.amusement_park_3,
                descriptionResourceId = R.string.amusement_Park_3_description
            ),
            FunMenu (
                id = 4,
                titleResourceId = R.string.amusement_Park_4,
                imageResourceId = R.drawable.amusement_park_4,
                descriptionResourceId = R.string.amusement_Park_4_description
            ),
            FunMenu (
                id = 5,
                titleResourceId = R.string.amusement_Park_5,
                imageResourceId = R.drawable.amusement_park_5,
                descriptionResourceId = R.string.amusement_Park_5_description
            ),
            FunMenu (
                id = 6,
                titleResourceId = R.string.amusement_Park_6,
                imageResourceId = R.drawable.amusement_park_6,
                descriptionResourceId = R.string.amusement_Park_6_description
            )
        )
    )

    private val Cinemas = Category(
        id = 4,
        titleResourceId = R.string.cinema,
        descriptionResourceId = R.string.cinema_description,
        imageResourceId = R.drawable.cinema,
        categoryList = listOf(
            FunMenu(
                id = 1,
                titleResourceId = R.string.cinema_1,
                imageResourceId = R.drawable.cinema_1,
                descriptionResourceId = R.string.cinema_1_description
            ),
            FunMenu(
                id = 2,
                titleResourceId = R.string.cinema_2,
                imageResourceId = R.drawable.cinema_2,
                descriptionResourceId = R.string.cinema_2_description
            ),
            FunMenu(
                id = 3,
                titleResourceId = R.string.cinema_3,
                imageResourceId = R.drawable.cinema_3,
                descriptionResourceId = R.string.cinema_3_description
            ),
            FunMenu(
                id = 4,
                titleResourceId = R.string.cinema_4,
                imageResourceId = R.drawable.cinema_4,
                descriptionResourceId = R.string.cinema_4_description
            ),
            FunMenu(
                id = 5,
                titleResourceId = R.string.cinema_5,
                imageResourceId = R.drawable.cinema_5,
                descriptionResourceId = R.string.cinema_5_description
            ),
            FunMenu(
                id = 6,
                titleResourceId = R.string.cinema_6,
                imageResourceId = R.drawable.cinema_6,
                descriptionResourceId = R.string.cinema_6_description
            ),
        )
    )

    private val ShoppingMalls = Category(
        id = 5,
        titleResourceId = R.string.shopping_malls,
        descriptionResourceId = R.string.shopping_malls_description,
        imageResourceId = R.drawable.shopping_malls,
        categoryList = listOf(
            FunMenu(
                id = 1,
                titleResourceId = R.string.shopping_Mall_1,
                imageResourceId = R.drawable.shopping_mall_1,
                descriptionResourceId = R.string.shopping_Mall_1_description
            ),
            FunMenu(
                id = 2,
                titleResourceId = R.string.shopping_Mall_2,
                imageResourceId = R.drawable.shopping_mall_2,
                descriptionResourceId = R.string.shopping_Mall_2_description
            ),
            FunMenu(
                id = 3,
                titleResourceId = R.string.shopping_Mall_3,
                imageResourceId = R.drawable.shopping_mall_3,
                descriptionResourceId = R.string.shopping_Mall_3_description
            ),
            FunMenu(
                id = 4,
                titleResourceId = R.string.shopping_Mall_4,
                imageResourceId = R.drawable.shopping_mall_4,
                descriptionResourceId = R.string.shopping_Mall_4_description
            ),
            FunMenu(
                id = 5,
                titleResourceId = R.string.shopping_Mall_5,
                imageResourceId = R.drawable.shopping_mall_5,
                descriptionResourceId = R.string.shopping_Mall_5_description
            ),
            FunMenu(
                id = 6,
                titleResourceId = R.string.shopping_Mall_6,
                imageResourceId = R.drawable.shopping_mall_6,
                descriptionResourceId = R.string.shopping_Mall_6_description
            ),
            FunMenu(
                id = 7,
                titleResourceId = R.string.shopping_Mall_7,
                imageResourceId = R.drawable.shopping_mall_7,
                descriptionResourceId = R.string.shopping_Mall_7_description
            )
        )
    )

    private val Hotels = Category(
        id = 6,
        titleResourceId = R.string.hotels,
        descriptionResourceId = R.string.hotels_description,
        imageResourceId = R.drawable.hotels,
        categoryList = listOf(
            FunMenu(
                id = 1,
                titleResourceId = R.string.hotel_1,
                imageResourceId = R.drawable.hotel_1,
                descriptionResourceId = R.string.hotel_1_description
            ),
            FunMenu(
                id = 2,
                titleResourceId = R.string.hotel_2,
                imageResourceId = R.drawable.hotel_2,
                descriptionResourceId = R.string.hotel_2_description
            ),
            FunMenu(
                id = 3,
                titleResourceId = R.string.hotel_3,
                imageResourceId = R.drawable.hotel_3,
                descriptionResourceId = R.string.hotel_3_description
            ),
            FunMenu(
                id = 4,
                titleResourceId = R.string.hotel_4,
                imageResourceId = R.drawable.hotel_4,
                descriptionResourceId = R.string.hotel_4_description
            ),
            FunMenu(
                id = 5,
                titleResourceId = R.string.hotel_5,
                imageResourceId = R.drawable.hotel_5,
                descriptionResourceId = R.string.hotel_5_description
            ),
            FunMenu(
                id = 6,
                titleResourceId = R.string.hotel_6,
                imageResourceId = R.drawable.hotel_6,
                descriptionResourceId = R.string.hotel_6_description
            )
        )
    )

    private val Museums = Category(
        id = 7,
        titleResourceId = R.string.museum,
        descriptionResourceId = R.string.museum_description,
        imageResourceId = R.drawable.museum,
        categoryList = listOf(
            FunMenu(
                id = 1,
                titleResourceId = R.string.museum_1,
                imageResourceId = R.drawable.museum_1,
                descriptionResourceId = R.string.museum_1_description
            ),
            FunMenu(
                id = 2,
                titleResourceId = R.string.museum_2,
                imageResourceId = R.drawable.museum_2,
                descriptionResourceId = R.string.museum_2_description
            ),
            FunMenu(
                id = 3,
                titleResourceId = R.string.museum_3,
                imageResourceId = R.drawable.museum_3,
                descriptionResourceId = R.string.museum_3_description
            ),
            FunMenu(
                id = 4,
                titleResourceId = R.string.museum_4,
                imageResourceId = R.drawable.museum_4,
                descriptionResourceId = R.string.museum_4_description
            ),
            FunMenu(
                id = 5,
                titleResourceId = R.string.museum_5,
                imageResourceId = R.drawable.museum_5,
                descriptionResourceId = R.string.museum_5_description
            ),
            FunMenu(
                id = 6,
                titleResourceId = R.string.museum_6,
                imageResourceId = R.drawable.museum_6,
                descriptionResourceId = R.string.museum_6_description
            )
        )
    )

    val categoryList = listOf(
        CoffeeShops,
        Restaurants,
        Gyms,
        AmusementParks,
        Cinemas,
        ShoppingMalls,
        Hotels,
        Museums
    )
}