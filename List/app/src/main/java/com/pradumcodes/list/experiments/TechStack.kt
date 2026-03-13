package com.pradumcodes.list.experiments

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pradumcodes.list.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TechStack() {

    val spinnerItems = listOf("Languages", "Frameworks", "Databases")
    val languagesItems = listOf("Java", "Kotlin", "Python", "C++", "Javascript")
    val stackItems = listOf("Android", "Flutter", "React", "Firebase", "MongoDB", "NodeJS")

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Android UI Components") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { padding ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            item {
                SpinnerCard(spinnerItems)
            }

            item {
                SectionTitle("Programming Languages")
                ProgrammingLanguagesCard(languagesItems)
            }

            item {
                SectionTitle("Technology Stack")
                TechnologyStackCard(stackItems)
            }

            item { Spacer(Modifier.height(16.dp)) }
        }
    }
}

@Composable
fun SectionTitle(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.titleMedium,
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier.padding(vertical = 6.dp)
    )
}

@Composable
fun SpinnerCard(items: List<String>) {

    var expanded by remember { mutableStateOf(false) }
    var selectedItem by remember { mutableStateOf(items.first()) }

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        elevation = CardDefaults.cardElevation(4.dp)
    ){

        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            OutlinedTextField(
                value = selectedItem,
                onValueChange = {},
                readOnly = true,
                label = { Text("Select Category") },
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "Dropdown",
                        modifier = Modifier
                            .clickable { expanded = true}
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { expanded = true }
            )


            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                items.forEach { item ->
                    DropdownMenuItem(
                        text = { Text(item) },
                        onClick = {
                            selectedItem = item
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun ProgrammingLanguagesCard(items: List<String>) {

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer
        ),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {

        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            items.forEach { item ->
                Text(
                    text = item,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}

@Composable
fun TechnologyStackCard(items: List<String>) {

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.tertiaryContainer
        ),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {

        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier
                .padding(12.dp)
                .height(180.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            items(items) { item ->

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .aspectRatio(1f)
                        .clickable { }
                ) {

                    Icon(
                        painter = iconForItem(item),
                        contentDescription = item,
                        modifier = Modifier.size(36.dp),
                        tint = MaterialTheme.colorScheme.onTertiaryContainer
                    )

                    Spacer(Modifier.height(8.dp))

                    Text(
                        text = item,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}
@Composable
private fun iconForItem(name: String): Painter {
    return when {
        name.contains("android", ignoreCase = true) -> painterResource(id = R.drawable.ic_launcher_foreground)
        name.contains("react", ignoreCase = true) -> painterResource(id = R.drawable.ic_launcher_foreground)
        else -> painterResource(id = R.drawable.ic_launcher_foreground)
    }
}

@Preview(showBackground = true)
@Composable
fun TechStackPreview() {
    TechStack()
}