package com.pradumcodes.list.campusfeedbackapp.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color

// Show Subjects in a grid with 2 columns

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CourseScreen(
    onCourseSelected: (String) -> Unit = {},
    onBack: () -> Unit = {}
) {
    // Sample subjects - replace with real data if you have it
    val subjects = listOf(
        "Mathematics",
        "Physics",
        "Chemistry",
        "Biology",
        "Computer Science",
        "History",
        "Economics",
        "English"
    )

    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.Transparent)
        .padding(8.dp)
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(subjects) { subject ->
                CourseCard(name = subject, onClick = { onCourseSelected(subject) })
            }
        }
    }
}

@Composable
private fun CourseCard(name: String, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .height(120.dp)
            .fillMaxWidth()
            .clickable { onClick() },
    ) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(
                text = name,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}