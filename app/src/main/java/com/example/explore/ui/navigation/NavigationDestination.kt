package com.example.explore.ui.navigation

interface Destination {
    val route: String
}

val Screens = listOf(
    Welcome,
    Category,
    Menu,
    Favorite,
    Detail,
    ExpandedCategory,
    ExpandedMenu
)


object Welcome : Destination {
    override val route = "welcome"
}

object Category : Destination {
    override val route = "category"
}

object Menu : Destination {
    override val route = "menu"
}

object Favorite : Destination {
    override val route = "favorite"
}

object Detail : Destination {
    override val route = "detail"
}

object ExpandedCategory : Destination {
    override val route = "expandedCategory"
}

object ExpandedMenu : Destination {
    override val route = "expandedMenu"
}