package com.isverbit.notespet.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.isverbit.notespet.NoteViewModel
import com.isverbit.notespet.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ViewNoteScreen(navController: NavController, noteId: Int, noteViewModel: NoteViewModel) {
    val note by noteViewModel.getNoteById(noteId).collectAsState(initial = null)

    note?.let {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("View Note") },
                    actions = {
                        IconButton(onClick = {
                            navController.navigate("${Screen.EditNote.route}/$noteId")
                        }) {
                            Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit Note")
                        }
                        IconButton(onClick = {
                            noteViewModel.deleteNote(note!!)
                            navController.navigate(Screen.Main.route) {
                                popUpTo(Screen.Main.route) { inclusive = true }
                            }
                        }) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = "Delete Note"
                            )
                        }
                    }
                )
            },
            content = { padding ->
                Column(
                    modifier = Modifier
                        .padding(padding)
                        .padding(16.dp)
                ) {
                    Text(
                        text = it.title,
                        style = MaterialTheme.typography.headlineSmall,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    Text(
                        text = it.content,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        )
    }
}