package com.pradumcodes.list.experiments

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pradumcodes.list.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ICC() {

    val teams = listOf(
        "India", "New Zealand", "Australia", "England", "South Africa",
        "Pakistan", "Bangladesh", "Sri Lanka", "West Indies", "Afghanistan"
    )

    val flagMap = mapOf(
        "India" to R.drawable.`in`,
        "New Zealand" to R.drawable.nz,
        "Australia" to R.drawable.au,
        "England" to R.drawable.en,
        "South Africa" to R.drawable.sa,
        "Pakistan" to R.drawable.pa,
        "Bangladesh" to R.drawable.ba,
        "Sri Lanka" to R.drawable.sl,
        "West Indies" to R.drawable.wi,
        "Afghanistan" to R.drawable.af
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("ICC Team Rankings") }
            )
        }
    ) { padding ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {

            item {

                Image(
                    painter = painterResource(R.drawable.cricket),
                    contentDescription = "Cricket",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.height(12.dp))
            }

            itemsIndexed(teams) { index, team ->

                val flag = flagMap[team] ?: R.drawable.`in`

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = "${index + 1}",
                        fontSize = 18.sp,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.width(28.dp)
                    )

                    Image(
                        painter = painterResource(flag),
                        contentDescription = "$team flag",
                        modifier = Modifier
                            .size(36.dp)
                    )

                    Spacer(modifier = Modifier.width(16.dp))

                    Text(
                        text = team,
                        fontSize = 18.sp,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }

//                Divider(
//                    modifier = Modifier.padding(horizontal = 16.dp),
//                    thickness = 0.6.dp
//                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ICCPreview() {
    ICC()
}