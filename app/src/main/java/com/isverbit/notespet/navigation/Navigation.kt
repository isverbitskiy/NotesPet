package com.isverbit.notespet.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.isverbit.notespet.NoteViewModel
import com.isverbit.notespet.screens.EditNoteScreen
import com.isverbit.notespet.screens.MainScreen
import com.isverbit.notespet.screens.ViewNoteScreen

@Composable
fun Navigation(navController: NavHostController, noteViewModel: NoteViewModel) {
    NavHost(navController, startDestination = Screen.Main.route) {
        composable(Screen.Main.route) {
            MainScreen(navController, noteViewModel)
        }
        composable(Screen.EditNote.route) {
            EditNoteScreen(navController, null, noteViewModel)
        }
        composable(Screen.ViewNote.route + "/{noteId}") { backStackEntry ->
            val noteId = backStackEntry.arguments?.getString("noteId")?.toIntOrNull()
            if (noteId != null) {
                ViewNoteScreen(navController, noteId, noteViewModel)
            }
        }
        composable(Screen.EditNote.route + "/{noteId}") { backStackEntry ->
            val noteId = backStackEntry.arguments?.getString("noteId")?.toIntOrNull()
            if (noteId != null) {
                EditNoteScreen(navController, noteId, noteViewModel)
            }
        }
    }
}