package com.pradumcodes.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun OrderService() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "orderScreen"
    ) {
        composable("orderScreen") {
            OrderScreen(navController)
        }

        composable(
            route = "orderDetailsScreen/{orderName}/{orderQuantity}",
            arguments = listOf(
                navArgument("orderName") {
                    type = NavType.StringType
                },
                navArgument("orderQuantity") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->
            val name = backStackEntry.arguments?.getString("orderName")
            val quantity = backStackEntry.arguments?.getInt("orderQuantity")

            OrderDetailsScreen(name, quantity, navController)
        }
    }
}

@Composable
fun OrderScreen(navController: NavController) {
    var name by remember { mutableStateOf("") }
    var quantity by remember { mutableStateOf("") }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        TextField(value = name, onValueChange = { name = it })
        TextField(value = quantity, onValueChange = { quantity = it })

        Button(
            onClick = {
                val qty = quantity.toIntOrNull() ?: 0
                navController.navigate("orderDetailsScreen/$name/$qty")
            }
        ) {
            Text("Order")
        }
    }
}

@Composable
fun OrderDetailsScreen(name: String?, quantity: Int?, navController: NavController){
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Text("Order Details")
        Text("Name: $name")
        Text("Quantity: $quantity")
        Button(
            onClick = {
                navController.popBackStack()
            }
        ) {
            Text("Go Back")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OrderServicePreview(){
    OrderScreen(rememberNavController())
}