package com.hynekbraun.randomanimalscompose1.presentation.navigation

sealed class NavScreen(val route: String) {
    object ListScreen : NavScreen("list_screen")
    object DetailScreen : NavScreen("detail_screen")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }

}
