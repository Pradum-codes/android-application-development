package com.pradumcodes.notes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.pradumcodes.notes.data.Note
import com.pradumcodes.notes.presentation.NotesViewmodel
import com.pradumcodes.notes.ui.theme.NotesTheme
import dagger.hilt.android.AndroidEntryPoint
import androidx.compose.ui.unit.dp

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: NotesViewmodel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val uiState by viewModel.uiState.collectAsState()
            val notes = uiState.notes

            NotesTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NotesApp(
                        viewModel = viewModel,
                        notes = notes,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotesApp(modifier: Modifier = Modifier, viewModel: NotesViewmodel? = null, notes: List<Note> = emptyList()) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Notes") })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                viewModel?.addNote("Note ${notes.size + 1}", "Sample content")
            }) {
                Text("+")
            }
        },
        modifier = modifier.fillMaxSize()
    ) { contentPadding ->
        if (notes.isEmpty()) {
            Box(modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding), contentAlignment = Alignment.Center) {
                Text("No notes yet", style = MaterialTheme.typography.bodyLarge)
            }
        } else {
            LazyColumn(modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)) {
                items(notes) { note ->
                    Card(modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 8.dp)) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(note.title, style = MaterialTheme.typography.titleMedium)
                            Spacer(Modifier.height(4.dp))
                            Text(note.content, style = MaterialTheme.typography.bodyMedium)
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NotesAppPreview() {
    NotesTheme {
        Surface {
            NotesApp(notes = listOf(Note(title = "Sample Note", content = "This is a sample.")))
        }
    }
}
