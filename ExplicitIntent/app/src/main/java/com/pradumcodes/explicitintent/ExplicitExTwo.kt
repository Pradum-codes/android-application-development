package com.pradumcodes.explicitintent

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.app.ComponentActivity

class ExplicitExTwo : ComponentActivity() {

}

@Composable
fun RecieveDataScreen(name: String, email: String){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("${name}")
        Spacer(modifier = Modifier.padding(20.dp))
        Text("${email}")
    }
}