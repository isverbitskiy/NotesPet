package com.isverbit.notespet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import com.isverbit.notespet.navigation.Navigation
import com.isverbit.notespet.ui.theme.NotesPetTheme

class MainActivity : ComponentActivity() {
    private lateinit var noteViewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val noteDao = NoteDatabase.getDatabase(applicationContext).noteDao()
        noteViewModel =
            ViewModelProvider(this, NoteViewModelFactory(noteDao)).get(NoteViewModel::class.java)

        setContent {
            NotesPetTheme {
                val navController = rememberNavController()
                Navigation(navController, noteViewModel)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NotesPetTheme {
        // Provide a preview
    }
}