package com.isverbit.notespet.navigation

sealed class Screen(val route: String) {
    object Main : Screen("main_screen")
    object EditNote : Screen("edit_note_screen")
    object ViewNote : Screen("view_note_screen")
}
