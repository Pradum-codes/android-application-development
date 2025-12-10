package com.example.intentapplication

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent { IntentApp() }
    }
}

/* ----------------- Helpers ------------------ */

private fun Context.safeStartActivity(intent: Intent) {
    try {
        startActivity(intent)
    } catch (e: ActivityNotFoundException) {
        Toast.makeText(this, "No app can handle this action.", Toast.LENGTH_SHORT).show()
    }
}

/* ----------------- Root Screen ------------------ */

@Composable
fun IntentApp() {
    val scroll = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(scroll)
    ) {
        IntentCard(title = "Open Web Profile") { ProfileSection() }
        IntentCard(title = "Dial a Phone Number") { CallSection() }
        IntentCard(title = "Send SMS Message") { SmsSection() }
        IntentCard(title = "Pick Image From Gallery") { GallerySection() }
        IntentCard(title = "Send Email") { EmailSection() }
        IntentCard(title = "Share Text") { ShareSection() }

        Spacer(modifier = Modifier.height(24.dp))
    }
}

/* ---------------- Reusable Card Wrapper ---------------- */

@Composable
fun IntentCard(title: String, content: @Composable () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {

            Text(
                text = title,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 12.dp)
            )

            content()
        }
    }
}

/* ---------------- Section Implementations ---------------- */

@Composable
fun ProfileSection() {
    val context = LocalContext.current
    var username by remember { mutableStateOf("") }

    OutlinedTextField(
        value = username,
        onValueChange = { username = it },
        placeholder = { Text("LeetCode username") },
        modifier = Modifier.fillMaxWidth()
    )
    Spacer(Modifier.height(8.dp))
    Button(
        modifier = Modifier.fillMaxWidth(),
        onClick = {
            if (username.isBlank()) {
                Toast.makeText(context, "Enter a username.", Toast.LENGTH_SHORT).show()
                return@Button
            }
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://leetcode.com/u/$username"))
            context.safeStartActivity(intent)
        }
    ) {
        Text("Open Profile")
    }
}

@Composable
fun CallSection() {
    val context = LocalContext.current
    var phone by remember { mutableStateOf("") }

    OutlinedTextField(
        value = phone,
        onValueChange = { phone = it },
        placeholder = { Text("Phone number") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
        modifier = Modifier.fillMaxWidth()
    )
    Spacer(Modifier.height(8.dp))
    Button(
        modifier = Modifier.fillMaxWidth(),
        onClick = {
            if (phone.isBlank()) {
                Toast.makeText(context, "Enter number.", Toast.LENGTH_SHORT).show()
                return@Button
            }
            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phone"))
            context.safeStartActivity(intent)
        }
    ) {
        Text("Dial Number")
    }
}

@Composable
fun SmsSection() {
    val context = LocalContext.current
    var phone by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }

    OutlinedTextField(
        value = phone,
        onValueChange = { phone = it },
        placeholder = { Text("Phone number") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
        modifier = Modifier.fillMaxWidth()
    )
    Spacer(Modifier.height(8.dp))
    OutlinedTextField(
        value = message,
        onValueChange = { message = it },
        placeholder = { Text("Message text") },
        modifier = Modifier.fillMaxWidth()
    )
    Spacer(Modifier.height(8.dp))
    Button(
        modifier = Modifier.fillMaxWidth(),
        onClick = {
            if (phone.isBlank()) {
                Toast.makeText(context, "Enter phone.", Toast.LENGTH_SHORT).show()
                return@Button
            }
            val intent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("smsto:$phone")
                putExtra("sms_body", message)
            }
            context.safeStartActivity(intent)
        }
    ) {
        Text("Send SMS")
    }
}

@Composable
fun GallerySection() {
    val context = LocalContext.current
    Button(
        modifier = Modifier.fillMaxWidth(),
        onClick = {
            val intent = Intent(Intent.ACTION_PICK).apply { type = "image/*" }
            context.safeStartActivity(intent)
        }
    ) {
        Text("Pick Image")
    }
}

@Composable
fun EmailSection() {
    val context = LocalContext.current
    var email by remember { mutableStateOf("") }
    var body by remember { mutableStateOf("") }

    OutlinedTextField(
        value = email,
        onValueChange = { email = it },
        placeholder = { Text("Recipient email") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        modifier = Modifier.fillMaxWidth()
    )
    Spacer(Modifier.height(8.dp))
    OutlinedTextField(
        value = body,
        onValueChange = { body = it },
        placeholder = { Text("Email message") },
        modifier = Modifier.fillMaxWidth()
    )
    Spacer(Modifier.height(8.dp))
    Button(
        modifier = Modifier.fillMaxWidth(),
        onClick = {
            if (email.isBlank()) {
                Toast.makeText(context, "Enter email.", Toast.LENGTH_SHORT).show()
                return@Button
            }
            val intent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:$email")
                putExtra(Intent.EXTRA_SUBJECT, "Test Mail")
                putExtra(Intent.EXTRA_TEXT, body)
            }
            context.safeStartActivity(intent)
        }
    ) {
        Text("Send Email")
    }
}

@Composable
fun ShareSection() {
    val context = LocalContext.current

    Button(
        modifier = Modifier.fillMaxWidth(),
        onClick = {
            val intent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_TEXT, "Sharing text using implicit intent")
            }
            val chooser = Intent.createChooser(intent, "Share using:")
            context.safeStartActivity(chooser)
        }
    ) {
        Text("Share Text")
    }
}

@Preview(showBackground = true)
@Composable
fun IntentAppPreview() {
    IntentApp()
}
