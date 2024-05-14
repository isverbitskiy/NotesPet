package com.isverbit.notespet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.isverbit.notespet.navigation.Navigation
import com.isverbit.notespet.ui.theme.NotesPetTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NotesPetTheme {
                Navigation()
            }
        }
    }
}
