package com.pradumcodes.switchslider

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

open annotation class ComponentActivity

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