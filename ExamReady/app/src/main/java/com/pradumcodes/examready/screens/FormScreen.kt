package com.pradumcodes.examready.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun FormScreen(navController: NavController, name: String) {
    val context = LocalContext.current

    var isStudent by remember { mutableStateOf(false) }
    var rating by remember { mutableStateOf(5f) }
    var gender by remember { mutableStateOf("Male") }

    Column(Modifier.padding(16.dp)) {
        Text("Hello $name")

        Checkbox(
            checked = isStudent,
            onCheckedChange = { isStudent = it }
        )
        Text("Are you a student?")

        Row {
            RadioButton(
                selected = gender == "Male",
                onClick = { gender = "Male" }
            )
            Text("Male")

            RadioButton(
                selected = gender == "Female",
                onClick = { gender = "Female" }
            )
            Text("Female")
        }

        Slider(
            value = rating,
            onValueChange = { rating = it },
            valueRange = 0f..10f
        )

        Button(
            onClick = {
                Toast.makeText(
                    context,
                    "Form Submitted",
                    Toast.LENGTH_SHORT
                ).show()

                navController.navigate("summary/$name")
            }
        ) {
            Text("Submit")
        }
    }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(16.dp)
    ) { }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.padding(16.dp)
    ) { }
}
