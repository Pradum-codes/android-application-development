package com.pradumcodes.list

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import com.pradumcodes.list.experiments.TechStack
import com.pradumcodes.list.spinner.Spinner
import com.pradumcodes.list.views.Course
import com.pradumcodes.list.views.Courses
import com.pradumcodes.list.views.ListApp

// ── Palette ──────────────────────────────────────────────────────────────────


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
//            ListApp()
//            Spinner()
            TechStack()
//            Courses()
//            Spinner()
        }
    }
}


// ── Preview ───────────────────────────────────────────────────────────────────
@Preview(showBackground = true, backgroundColor = 0xFF0D0F14)
@Composable
fun DefaultPreview() {
    ListApp()
}