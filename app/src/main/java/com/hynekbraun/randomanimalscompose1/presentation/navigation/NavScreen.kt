package com.hynekbraun.randomanimalscompose1.presentation.navigation

sealed class NavScreen(val route: String) {
    object ListScreen : NavScreen("list_screen")
    object DetailScreen : NavScreen("detail_screen")
}
