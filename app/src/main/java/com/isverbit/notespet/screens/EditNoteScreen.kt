package com.isverbit.notespet.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.isverbit.notespet.Note
import com.isverbit.notespet.NoteViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditNoteScreen(
    navController: NavController,
    noteId: Int?,
    noteViewModel: NoteViewModel = viewModel()
) {
    var noteContent by remember { mutableStateOf("") }
    var isEditMode by remember { mutableStateOf(false) }

    val note by noteViewModel.getNoteById(noteId ?: -1).collectAsState(initial = null)

    LaunchedEffect(note) {
        note?.let {
            isEditMode = true
            noteContent = it.content
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(if (isEditMode) "Edit Note" else "New Note") },
                actions = {
                    IconButton(onClick = {
                        if (isEditMode && noteId != null) {
                            noteViewModel.updateNote(Note(id = noteId, content = noteContent))
                        } else {
                            noteViewModel.addNote(noteContent)
                        }
                        navController.popBackStack()
                    }) {
                        Icon(imageVector = Icons.Default.Save, contentDescription = "Save Note")
                    }
                }
            )
        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(padding)
                    .padding(16.dp)
            ) {
                OutlinedTextField(
                    value = noteContent,
                    onValueChange = { noteContent = it },
                    label = { Text("Note Content") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    )
}