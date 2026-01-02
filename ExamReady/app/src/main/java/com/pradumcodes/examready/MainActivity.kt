package com.pradumcodes.examready

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.pradumcodes.examready.navigation.AppNavGraph
import com.pradumcodes.examready.ui.theme.ExamReadyTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ExamReadyTheme {
                StudentApp()
//                DashboardApp()
            }
        }
    }
}

@Composable
fun StudentApp() {
    AppNavGraph()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardApp() {

    // Snackbar controller
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    // Bottom navigation state
    var selectedIndex by remember { mutableStateOf(0) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Student Dashboard") },
            )
        },

        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = selectedIndex == 0,
                    onClick = {
                        selectedIndex = 0
                        scope.launch {
                            snackbarHostState.showSnackbar("Home Selected")
                        }
                    },
                    icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                    label = { Text("Home") }
                )

                NavigationBarItem(
                    selected = selectedIndex == 1,
                    onClick = {
                        selectedIndex = 1
                        scope.launch {
                            snackbarHostState.showSnackbar("Alerts Selected")
                        }
                    },
                    icon = { Icon(Icons.Default.Notifications, contentDescription = "Alerts") },
                    label = { Text("Alerts") }
                )

                NavigationBarItem(
                    selected = selectedIndex == 2,
                    onClick = {
                        selectedIndex = 2
                        scope.launch {
                            snackbarHostState.showSnackbar("Settings Selected")
                        }
                    },
                    icon = { Icon(Icons.Default.Settings, contentDescription = "Settings") },
                    label = { Text("Settings") }
                )
            }
        },

        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }

    ) { paddingValues ->

        DashboardContent(
            modifier = Modifier.padding(paddingValues),
            selectedIndex = selectedIndex,
            onShowSnackbar = {
                scope.launch {
                    snackbarHostState.showSnackbar(it)
                }
            }
        )
    }
}

@Composable
fun DashboardContent(
    modifier: Modifier,
    selectedIndex: Int,
    onShowSnackbar: (String) -> Unit
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when (selectedIndex) {
            0 -> HomeScreen(onShowSnackbar)
            1 -> AlertScreen()
            2 -> SettingsScreen()
        }
    }
}

@Composable
fun HomeScreen(onShowSnackbar: (String) -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Welcome, Pradum")
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            onShowSnackbar("Dashboard Refreshed")
        }) {
            Text("Refresh Dashboard")
        }
    }
}

@Composable
fun AlertScreen() {
    Text("No new notifications")
}

@Composable
fun SettingsScreen() {
    Text("Settings are hardcoded")
}