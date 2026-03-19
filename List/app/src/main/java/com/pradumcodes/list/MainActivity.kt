package com.pradumcodes.list

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import com.pradumcodes.list.campusfeedbackapp.CampusFeedbackApp
import com.pradumcodes.list.progressbar.ProgressBarScreen
import com.pradumcodes.list.views.ListApp
import com.pradumcodes.list.views.SplashApp

// ── Palette ──────────────────────────────────────────────────────────────────


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
//            ListApp()
//            Spinner()
//            TechStack()
//            Courses()
//            Spinner()
//            ProgressBarScreen()
//            MenuApp()
//            SplashApp()
            CampusFeedbackApp()
        }
    }
}


// ── Preview ───────────────────────────────────────────────────────────────────
@Preview(showBackground = true, backgroundColor = 0xFF0D0F14)
@Composable
fun DefaultPreview() {
    ListApp()
}