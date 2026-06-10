package com.pradumcodes.alarmclock

import android.app.AlarmManager
import android.content.Context
import android.content.Intent
import android.provider.Settings
import android.os.Build
import androidx.annotation.RequiresApi

@RequiresApi(Build.VERSION_CODES.S)
fun checkExactAlarmPermission(context: Context) {
    val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    if(!alarmManager.canScheduleExactAlarms()){
        val intent = Intent(Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM)
        context.startActivity(intent)
    }
}