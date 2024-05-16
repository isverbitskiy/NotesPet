package com.isverbit.notespet.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.isverbit.notespet.Note
import com.isverbit.notespet.NoteViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditNoteScreen(navController: NavController, noteId: Int?, noteViewModel: NoteViewModel) {
    var title by remember { mutableStateOf("") }
    var content by remember { mutableStateOf("") }
    var showWarning by remember { mutableStateOf(false) }

    noteId?.let {
        val note by noteViewModel.getNoteById(it).collectAsState(initial = null)
        note?.let {
            title = it.title
            content = it.content
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Edit Note") },
                actions = {
                    IconButton(onClick = {
                        if (title.isNotBlank()) {
                            if (noteId == null) {
                                noteViewModel.addNote(
                                    title = title,
                                    content = content
                                )
                            } else {
                                noteViewModel.updateNote(
                                    Note(
                                        id = noteId,
                                        title = title,
                                        content = content
                                    )
                                )
                            }
                            navController.popBackStack()
                        } else {
                            showWarning = true
                        }
                    }) {
                        Icon(imageVector = Icons.Default.Save, contentDescription = "Save Note")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(padding)
                .padding(16.dp)
        ) {
            if (showWarning) {
                Text(
                    text = "Title is required",
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall
                )
            }
            TextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Title") },
                modifier = Modifier.fillMaxWidth()
            )
            TextField(
                value = content,
                onValueChange = { content = it },
                label = { Text("Content") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            )
        }
    }
}