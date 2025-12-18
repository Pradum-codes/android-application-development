package com.pradumcodes.navigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.pradumcodes.navigation.ui.theme.NavigationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
//            NavEx()
            OrderService()
        }
    }
}

@Composable
fun NavEx(){
    val navController = rememberNavController();
    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {
            HomeScreen(navController)
        }
        composable (
            route ="details/{name}",
            arguments = listOf(
                navArgument("name"){
                    type = NavType.StringType
                }
            )
        ) { backstackentry ->
            val inp = backstackentry.arguments?.getString("name")
            DetailsScreen(name = inp, navController)
        }
    }
}

@Composable
fun HomeScreen(navController: NavController){

    var name by remember { mutableStateOf("") }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Text("Home Screen")
        TextField(value = name, onValueChange = {name = it})
        Spacer(Modifier.padding(24.dp))
        Button(
            onClick = {
                navController.navigate("details/$name")
            }
        ) {
            Text("Go to Welcome Screen")
        }
    }
}

@Composable
fun DetailsScreen(name: String?, navController: NavController){
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Text("Welcome $name")
        Spacer(Modifier.padding(24.dp))
        Button(
            onClick = {
                navController.popBackStack()
            }
        ) {
            Text("Go to Home screen")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NavigationTheme {
        NavEx()
    }
}