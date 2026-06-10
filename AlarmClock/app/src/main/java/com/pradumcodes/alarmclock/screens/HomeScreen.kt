package com.pradumcodes.alarmclock.screens

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.provider.Settings
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pradumcodes.alarmclock.AlarmReceiver
import java.util.Locale

data class AlarmItem(
    val id: Long,
    val hour: Int,
    val minute: Int,
    val label: String = "",
    val enabled: Boolean = true
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlarmScreen() {
    val selectedTime = remember { mutableStateOf("No Time Selected") }
    val showDialog = remember { mutableStateOf(false) }
    val timePickerState = rememberTimePickerState()

    val alarms by viewModel.alarms.collectAsState()
    AlarmList(
        alarmList = alarms,
        onToggle = { alarm, enabled -> viewModel.toggleAlarm(alarm.id, enabled) },
        onDelete = { alarm -> viewModel.deleteAlarm(alarm.id) }
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Alarm App",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = { showDialog.value = true }) {
            Text("Set Alarm")
        }

        Text(selectedTime.value)
    }

    TimePickerDialog(
        showDialog = showDialog,
        selectedTime = selectedTime,
        timePickerState = timePickerState
    )
}

@Composable
fun AlarmList(
    alarmList: List<AlarmItem>,
    onToggle: (AlarmItem, Boolean) -> Unit,
    onDelete: (AlarmItem) -> Unit
) {
    LazyColumn {
        items(alarmList, key = { it.id }) { alarm ->
            ListItem(
                headlineContent = { Text(String.format("%02d:%02d", alarm.hour, alarm.minute)) },
                supportingContent = { Text(alarm.label.ifBlank { "Alarm" }) },
                trailingContent = {
                    Switch(
                        checked = alarm.enabled,
                        onCheckedChange = { onToggle(alarm, it) }
                    )
                }
            )
            HorizontalDivider()
        }
    }
}


fun checkExactAlarmPermission(context: Context): Boolean {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.canScheduleExactAlarms()
    } else true
}
fun requestExactAlarmPermission(context: Context) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        try {
            val intent = Intent(Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM)
            context.startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(context, "Enable permission manually", Toast.LENGTH_LONG).show()
        }
    }
}
@SuppressLint("ScheduleExactAlarm")
fun setAlarm(context: Context, triggerAtMillis: Long) {

    val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

    val intent = Intent(context, AlarmReceiver::class.java)

    val pendingIntent = PendingIntent.getBroadcast(
        context,
        0,
        intent,
        PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
    )

    alarmManager.setExact(
        AlarmManager.RTC_WAKEUP,
        triggerAtMillis,
        pendingIntent
    )

    Toast.makeText(context, "Alarm Set Successfully!", Toast.LENGTH_SHORT).show()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimePickerDialog(showDialog : MutableState<Boolean>, selectedTime : MutableState<String>, timePickerState : TimePickerState ) {

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if(showDialog.value) {
            AlertDialog(
                onDismissRequest = { showDialog.value = false },
                confirmButton = {
                    TextButton(
                        onClick = {
                            val hour = timePickerState.hour
                            val minute = timePickerState.minute
                            /*selectedTime = String.format(
                                Locale.getDefault(),
                                "%02d:%02d",
                                hour,
                                minute
                            )*/
                            val amPm = if(hour < 12) "AM" else "PM"
                            val formattedHour = if (hour % 12 == 0) 12 else hour % 12
                            selectedTime.value = String.format(
                                Locale.getDefault(),
                                "%02d:%02d %s" ,
                                formattedHour,
                                minute,
                                amPm
                            )
                            showDialog.value = false
                        }
                    ) {
                        Text("OK")
                    }
                },
                dismissButton = {
                    TextButton(
                        onClick = {showDialog.value = false}
                    ) { Text("Cancel")}
                },
                text = {
                    TimePicker(state = timePickerState)
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AlarmAppPreview() {
    AlarmScreen()
}