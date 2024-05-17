package com.isverbit.notespet.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.isverbit.notespet.Note
import com.isverbit.notespet.NoteViewModel
import com.isverbit.notespet.R
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditNoteScreen(
    navController: NavController,
    noteId: Int?,
    noteViewModel: NoteViewModel
) {
    var title by remember { mutableStateOf("") }
    var content by remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()

    if (noteId != null && noteId != 0) {
        LaunchedEffect(noteId) {
            noteViewModel.getNoteById(noteId).collect { note ->
                note?.let {
                    title = it.title
                    content = it.content
                }
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.edit_note)) },
                actions = {
                    IconButton(onClick = {
                        if (title.isNotBlank()) {
                            coroutineScope.launch {
                                if (noteId != null && noteId != 0) {
                                    noteViewModel.updateNote(
                                        Note(
                                            id = noteId,
                                            title = title,
                                            content = content
                                        )
                                    )
                                } else {
                                    noteViewModel.addNote(
                                        title = title,
                                        content = content
                                    )
                                }
                                navController.popBackStack()
                            }
                        }
                    }) {
                        Icon(
                            imageVector = Icons.Default.Save, contentDescription = stringResource(
                                id = R.string.save_note
                            )
                        )
                    }
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.Close, contentDescription = stringResource(
                                id = R.string.close
                            )
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = { Text(text = stringResource(id = R.string.title_hint)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )
            OutlinedTextField(
                value = content,
                onValueChange = { content = it },
                label = { Text(text = stringResource(id = R.string.content_hint)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )
        }
    }
}