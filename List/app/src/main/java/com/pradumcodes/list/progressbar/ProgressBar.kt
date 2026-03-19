package com.pradumcodes.list.progressbar

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProgressBarScreen() {

    Scaffold(
        topBar = {
            TopAppBar(
                title = {Text("Progress Bar")},
            )
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            IndeterminateLinearProgress()

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(125.dp)
                    .border(1.dp, androidx.compose.ui.graphics.Color.Gray),

                contentAlignment = Alignment.Center
            ) {
                RatingApp()
            }
            // Circular Progress
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .border(1.dp, androidx.compose.ui.graphics.Color.Gray),

                contentAlignment = Alignment.Center
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(8.dp)
                ) {
                    Text("Circular Progress")
                    CircularProgressBarEx()
                }
            }

            // Linear Progress
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .border(1.dp, androidx.compose.ui.graphics.Color.Gray),

                contentAlignment = Alignment.Center
            ) {
                Text("Linear Progress")
                LinearProgressBar()
            }

            // InDeterminate Linear Progress
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .border(1.dp, androidx.compose.ui.graphics.Color.Gray),

                contentAlignment = Alignment.Center
            ) {
                Text("Indeterminate Progress")
                IndeterminateLinearProgress()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultProgressPreview() {
    ProgressBarScreen()
}