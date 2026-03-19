package com.pradumcodes.list.progressbar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun RatingApp() {
    var rating by remember { mutableDoubleStateOf(1.0) }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = "Rate this APP",
            fontSize = 24.sp,
        )
        Spacer(modifier = Modifier.height(16.dp))

        CustomRatingBar(
            rating = rating,
            onRatingChange = {
                rating = it.toDouble()
            }
        )

        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Selected Rating: $rating stars",
            fontSize = 18.sp,
        )
    }
}

@Composable
fun CustomRatingBar(
    maxRating: Int = 5,
    rating: Double,
    onRatingChange: (Int) -> Unit
) {
    Row{
        for(i in 1..maxRating) {
            Icon(
                imageVector = Icons.Filled.Star,
                contentDescription = "Star",
                tint = if( i<= rating) Color(0xFFFFC107) else Color.Gray,
                modifier = Modifier
                    .size(40.dp)
                    .clickable {
                        onRatingChange(i)
                    }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RatingAppPreview() {
    RatingApp()
}