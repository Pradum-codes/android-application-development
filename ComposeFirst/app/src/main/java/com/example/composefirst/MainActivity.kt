package com.example.composefirst

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LoginForm()
        }
    }
}

@Composable
fun LoginForm() {

    var name by remember { mutableStateOf("") }
    var regNo by remember { mutableStateOf("") }
    var section by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        // Header Image
        Image(
            painter = painterResource(R.drawable.ic_launcher_foreground),
            contentDescription = "Profile Image",
            colorFilter = ColorFilter.tint(Color.Green),
            modifier = Modifier
                .size(180.dp)
                .padding(bottom = 10.dp)
        )

        // Form Container
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {

            // Name Field
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
            ) {
                Text(
                    text = "Name:",
                    modifier = Modifier.width(90.dp)
                )

                TextField(
                    value = name,
                    onValueChange = { name = it },
                    placeholder = { Text("Enter full name") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    shape = RoundedCornerShape(12.dp)
                )
            }

            // Reg No Field
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
            ) {
                Text(
                    text = "Regn No.:",
                    modifier = Modifier.width(90.dp)
                )

                TextField(
                    value = regNo,
                    onValueChange = { regNo = it },
                    placeholder = { Text("Registration number") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    shape = RoundedCornerShape(12.dp)
                )
            }

            // Section Field
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
            ) {
                Text(
                    text = "Section:",
                    modifier = Modifier.width(90.dp)
                )

                TextField(
                    value = section,
                    onValueChange = { section = it },
                    placeholder = { Text("Section") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    shape = RoundedCornerShape(12.dp)
                )
            }

            // Buttons
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(onClick = {
                    // TODO: Submit Logic
                }) {
                    Text("Submit")
                }

                Button(onClick = {
                    name = ""
                    regNo = ""
                    section = ""
                }) {
                    Text("Clear")
                }
            }
        }
    }
}


@Composable
fun LoginForm2() {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
    ) {
        Image(
            painter = painterResource(R.drawable.ic_launcher_foreground),
            contentDescription = "Profile Image",
            colorFilter = ColorFilter.tint(Color.Blue),
            modifier = Modifier.size(96.dp),
        )

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column {
                Text(text = "Name:")
                Text(text = "Regn No.:")
                Text(text = "Section:")
            }

            Column {
                TextField(
                    value = "",
                    onValueChange = { },
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = {  }
                )
                TextField(
                    value = "",
                    onValueChange = {},
                    modifier = Modifier.fillMaxWidth(),
                )
                TextField(
                    value = "",
                    onValueChange = {},
                    modifier = Modifier.fillMaxWidth(),
                )
            }
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = {  },
            ) { Text(text = "Submit") }

            Button(
                onClick = {},
            ) { Text(text = "Clear") }
        }
    }
}


//@Composable
//fun Greeting(modifier: Modifier = Modifier, name: String, age: Int = 18) {
//    val context = LocalContext.current
//    Box(
//        modifier = modifier
//            .fillMaxSize(),
//        contentAlignment = Alignment.TopCenter,
//    ) {
//        Image(
//            painter = painterResource(R.drawable.background_image),
//            contentDescription = "Background Image",
//            contentScale = ContentScale.Crop,
//            modifier = Modifier.fillMaxSize()
//        )
//
//        Column(
//            horizontalAlignment = Alignment.CenterHorizontally,
//            verticalArrangement = Arrangement.Center,
//            modifier = Modifier
//                .padding(48.dp)
//        ) {
//            Image(
//                imageVector = Icons.Default.Person,
//                contentDescription = "Person Icon",
//                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary),
//                modifier = Modifier.size(64.dp)
//            )
//
//            Text(
//                text = "Hello, $name",
//                style = MaterialTheme.typography.titleMedium,
//                color = MaterialTheme.colorScheme.onBackground
//            )
//            Text(
//                text = "Age: $age",
//                style = MaterialTheme.typography.bodyMedium,
//                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
//            )
//            Button(
//                onClick = {
//                    Toast.makeText(context, "Button Clicked", Toast.LENGTH_SHORT).show()
//                },
//                colors = ButtonDefaults.buttonColors(
//                    containerColor = Color.Black,
//                    contentColor = Color.Cyan
//                ),
//                modifier = Modifier.padding(top = 16.dp)
//            ) {
//                Text(
//                    text = "Get a Greeting",
//                    style = MaterialTheme.typography.titleMedium,
//                )
//            }
//        }
//    }
//}

//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    ComposeFirstTheme {
//        Greeting(
//            name="Android",
//        )
//    }
//}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LoginForm();
}