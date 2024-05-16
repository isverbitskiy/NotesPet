package com.isverbit.notespet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import com.isverbit.notespet.screens.ViewNoteScreen
import com.isverbit.notespet.ui.theme.NotesPetTheme

class ViewNoteActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NotesPetTheme {
                val context = LocalContext.current.applicationContext
                val noteDao = NoteDatabase.getDatabase(context).noteDao()
                val noteViewModel: NoteViewModel = remember {
                    ViewModelProvider(
                        this,
                        NoteViewModelFactory(noteDao)
                    )[NoteViewModel::class.java]
                }
                val navController = rememberNavController()
                val noteId = intent.getIntExtra("noteId", -1)
                ViewNoteScreen(
                    navController = navController,
                    noteId = noteId,
                    noteViewModel = noteViewModel
                )
            }
        }
    }
}