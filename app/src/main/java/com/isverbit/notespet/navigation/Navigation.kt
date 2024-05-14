package com.isverbit.notespet.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.isverbit.notespet.screens.EditNoteScreen
import com.isverbit.notespet.screens.MainScreen
import com.isverbit.notespet.screens.ViewNoteScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.Main.route) {
        composable(route = Screen.Main.route) {
            MainScreen(navController = navController)
        }
        composable(route = Screen.EditNote.route) {
            EditNoteScreen(navController = navController, noteId = null)
        }
        composable(
            route = "${Screen.EditNote.route}/{noteId}",
            arguments = listOf(navArgument("noteId") { type = NavType.IntType })
        ) { backStackEntry ->
            val noteId = backStackEntry.arguments?.getInt("noteId")
            EditNoteScreen(navController = navController, noteId = noteId)
        }
        composable(
            route = "${Screen.ViewNote.route}/{noteId}",
            arguments = listOf(navArgument("noteId") { type = NavType.IntType })
        ) { backStackEntry ->
            val noteId = backStackEntry.arguments?.getInt("noteId") ?: -1
            ViewNoteScreen(navController = navController, noteId = noteId)
        }
    }
}