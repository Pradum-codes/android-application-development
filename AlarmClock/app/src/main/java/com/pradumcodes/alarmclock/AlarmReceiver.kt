package com.pradumcodes.alarmclock

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.net.Uri
import android.widget.Toast

class AlarmReceiver : BroadcastReceiver () {
    override fun onReceive(context: Context, intent: Intent?) {
        Toast.makeText(context, "Alarm Triggereed", Toast.LENGTH_SHORT).show()
        val alarmSound : Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)
        val ringtone = RingtoneManager.getRingtone(context, alarmSound)
        ringtone.play();
    }
}