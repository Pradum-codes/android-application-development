package com.pradumcodes.list.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class Course(
    val title: String,
    val semester: Int,
    val color: Color
)
val courses = listOf(
    Course("Python Programming", 1, Color.Red),
    Course("Data Structures", 1, Color.Red),
    Course("Operating Systems", 1, Color.Red),

    Course("Computer Programming", 2, Color.Yellow),
    Course("Database Systems", 2, Color.Yellow),
    Course("Computer Networks", 2, Color.Yellow),
)
@Composable
fun Courses() {

    val courses = listOf(
        Course("Python Programming", 1, Color.Red),
        Course("Data Structures", 1, Color.Red),
        Course("Operating Systems", 1, Color.Red),

        Course("Computer Programming", 2, Color.Yellow),
        Course("Database Systems", 2, Color.Yellow),
        Course("Computer Networks", 2, Color.Yellow),
    )

    val sem1 = courses.filter { it.semester == 1 }
    val sem2 = courses.filter { it.semester == 2 }

    val rows = maxOf(sem1.size, sem2.size)

    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        // Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Text("Semester 1")
            Text("Semester 2")
        }

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.padding(8.dp)
        ) {

            items(rows * 2) { index ->

                val row = index / 2
                val column = index % 2

                val course = if (column == 0) {
                    sem1.getOrNull(row)
                } else {
                    sem2.getOrNull(row)
                }

                if (course != null) {
                    CourseCard(course)
                } else {
                    Spacer(Modifier.height(120.dp))
                }
            }
        }
    }
}
@Composable
fun CourseCard(course: Course) {

    Card(
        modifier = Modifier
            .padding(8.dp)
            .height(120.dp),
        colors = CardDefaults.cardColors(
            containerColor = course.color
        ),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(course.title, fontSize = 16.sp)
        }
    }
}

@Composable
fun GridView() {

    val items = (1..16).map { "Items $it" }

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxSize()
            .padding(8.dp)
    ) {
        items(items) { item ->
            Card(
                modifier = Modifier
                    .padding(8.dp)
                    .height(120.dp),
                elevation = CardDefaults.cardElevation(6.dp)
            ) {
                Box(
                    contentAlignment = Alignment.Center
                ){
                    Text(text = item, fontSize = 18.sp)
                }
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun GridPreview() {
    GridView()
}

@Preview(showBackground = true)
@Composable
fun CoursePreview() {
    Courses()
}