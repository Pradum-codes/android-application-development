package com.pradumcodes.list.campusfeedbackapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    // internal lightweight navigation state
    var currentScreen by remember { mutableStateOf<Screen>(Screen.Home) }
    var selectedCourse by remember { mutableStateOf<String?>(null) }

    var expanded by remember { mutableStateOf(false) }
    var selectedItem: String by remember { mutableStateOf("None") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        when (currentScreen) {
                            Screen.Home -> "Campus Feedback"
                            Screen.Courses -> "Courses"
                            is Screen.Rating -> "Rate Course"
                        }, color = Color.White
                    )
                },
                modifier = Modifier.fillMaxWidth().background(Color.Blue),
                colors = TopAppBarDefaults.topAppBarColors(Color.Blue),
                // always provide a composable lambda; render the IconButton only when not on Home
                navigationIcon = {
                    if (currentScreen != Screen.Home) {
                        IconButton(onClick = {
                            // go back to home
                            currentScreen = Screen.Home
                            selectedCourse = null
                        }) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "Back",
                                tint = Color.White
                            )
                        }
                    }
                },
                actions = {
                    IconButton(
                        onClick = { expanded = true }
                    ) {
                        Icon(
                            imageVector = Icons.Default.MoreVert,
                            contentDescription = "Menu"
                        )
                    }
                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        DropdownMenuItem(
                            text = { Text("Settings") },
                            onClick = {
                                selectedItem = "Settings"
                                expanded = false
                            }
                        )
                        DropdownMenuItem(
                            text = { Text("About") },
                            onClick = {
                                selectedItem = "About"
                                expanded = false
                            }
                        )
                        DropdownMenuItem(
                            text = { Text("Log Out") },
                            onClick = {
                                selectedItem = "Log Out"
                                expanded = false
                            }
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
        ) {
            when (val screen = currentScreen) {
                Screen.Home -> {
                    HomeContent(
                        onOpenCourses = { currentScreen = Screen.Courses },
                        onOpenRating = {
                            selectedCourse = it
                            currentScreen = Screen.Rating(it)
                        }
                    )
                }
                Screen.Courses -> {
                    CourseScreen(
                        onCourseSelected = {
                            selectedCourse = it
                            currentScreen = Screen.Rating(it)
                        },
                        onBack = {
                            currentScreen = Screen.Home
                        }
                    )
                }
                is Screen.Rating -> {
                    RatingScreen(
                        course = screen.course,
                        onBack = {
                            // After rating, go back to home
                            currentScreen = Screen.Home
                            selectedCourse = null
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun HomeContent(
    onOpenCourses: () -> Unit,
    onOpenRating: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("Welcome to Campus Feedback", style = MaterialTheme.typography.headlineSmall)
        Text("Use the buttons below to view courses and give feedback.", style = MaterialTheme.typography.bodyMedium)
        ElevatedButton(onClick = onOpenCourses) {
            Text("View Courses")
        }
        ElevatedButton(onClick = { onOpenRating("General") }) {
            Text("Rate (Quick) - General")
        }
    }
}

private sealed class Screen {
    object Home : Screen()
    object Courses : Screen()
    data class Rating(val course: String) : Screen()
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}