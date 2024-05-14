package com.isverbit.notespet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.isverbit.notespet.ui.theme.NotesPetTheme

class ViewNoteActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NotesPetTheme {
                ViewNoteScreen(noteContent = "Это текст заметки")
            }
        }
    }
}

@Composable
fun ViewNoteScreen(noteContent: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = noteContent,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.weight(1f)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(onClick = { /* TODO: Edit note */ }, modifier = Modifier.weight(1f)) {
                Text("Редактировать")
            }
            Spacer(modifier = Modifier.width(16.dp))
            Button(onClick = { /* TODO: Delete note */ }, modifier = Modifier.weight(1f)) {
                Text("Удалить")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ViewNoteScreenPreview() {
    NotesPetTheme {
        ViewNoteScreen(noteContent = "Это текст заметки")
    }
}