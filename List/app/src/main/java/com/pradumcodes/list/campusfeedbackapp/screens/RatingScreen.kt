package com.pradumcodes.list.campusfeedbackapp.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun RatingScreen(
    course: String = "Course",
    onBack: () -> Unit = {}
) {
    var rating by remember { mutableStateOf(0) }
    var submitted by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(text = "Rate: $course", style = MaterialTheme.typography.headlineSmall)
        Text(text = "Tap the stars to rate (1-5)", style = MaterialTheme.typography.bodyMedium)

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            for (i in 1..5) {
                IconButton(onClick = { rating = i }) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "Star $i",
                        tint = if (i <= rating) Color(0xFFFFD700) else Color.Gray
                    )
                }
            }
        }

        OutlinedTextField(
            value = if (submitted) "Thanks for rating!" else "",
            onValueChange = {},
            label = { Text("Optional comment (not saved)") },
            enabled = !submitted,
            modifier = Modifier.fillMaxWidth()
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(
                onClick = {
                    // submit logic - in a real app send rating to backend or save locally
                    submitted = true
                },
                enabled = !submitted
            ) {
                Text(if (!submitted) "Submit" else "Submitted")
            }
            OutlinedButton(
                onClick = onBack
            ) {
                Text("Back")
            }
        }

        if (submitted) {
            Text("You rated $rating star(s) for $course", style = MaterialTheme.typography.bodyMedium)
        }
    }
}