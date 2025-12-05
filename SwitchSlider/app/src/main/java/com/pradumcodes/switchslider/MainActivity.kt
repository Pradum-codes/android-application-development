package com.pradumcodes.switchslider

import android.os.Bundle
import android.widget.RadioButton
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.RadioButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme {
                Surface {
                    Column {
//                        SimpleContent()
//                        SimpleCheckBox()
                        Form()
                    }
                }
            }
        }
    }
}

@Composable
fun SimpleContent() {
    var mode by remember { mutableStateOf(false) }
    var percent by remember { mutableStateOf(32F) }

    val options = listOf("Male", "Female", "Other")
    var selectedOption by remember { mutableStateOf(options[0]) }

    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Progress ${percent.toInt()}", modifier = Modifier.width(98.dp))
            Spacer(modifier = Modifier.width(8.dp))
            Slider(
                value = percent,
                onValueChange = { percent = it },
                valueRange = 0f..100f,
                modifier = Modifier.fillMaxWidth(),
            )
        }

        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(12.dp).fillMaxWidth()
            ) {
                Text(text = "Mode ${if (mode) "ON" else "OFF"}", modifier = Modifier.weight(1f))
                Switch(
                    checked = mode,
                    onCheckedChange = { mode = it },
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = Color.Blue,
                        checkedTrackColor = Color.Gray,
                        uncheckedThumbColor = Color.White,
                        uncheckedTrackColor = Color.LightGray
                    )
                )
            }
        }

        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth(),
        ){
            options.forEach { option ->
                Row (
                    verticalAlignment = Alignment.CenterVertically
                ){
                    RadioButton(
                        selected = (option == selectedOption),
                        onClick = { selectedOption = option}
                    )
                    Text(text = option, modifier = Modifier.padding(start = 8.dp))
                }
            }
        }

    }
}

@Composable
fun SimpleCheckBox(){
    val simpleCheckbox = listOf("Apple", "Grapes", "Banana")
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth(),
    ) {
        simpleCheckbox.forEach { option ->
            ClickableLabelCheckbox(option)
        }
    }
}

@Composable
fun ClickableLabelCheckbox(text : String){
    var checked by remember { mutableStateOf(false) }

    Row(
        verticalAlignment = Alignment.CenterVertically
    ){
        Checkbox(
            checked = checked,
            onCheckedChange = {checked=it}
        )
        Text(text = text)
    }
}

@Composable
fun MultipleCheckBox(){
        
}

@Preview(showBackground = true)
@Composable
fun PreviewSimple() {
    Column {
        SimpleContent()
        SimpleCheckBox()
    }
}