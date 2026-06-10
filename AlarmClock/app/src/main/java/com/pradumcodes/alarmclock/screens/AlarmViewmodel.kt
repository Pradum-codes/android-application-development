package com.pradumcodes.alarmclock.screens

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class AlarmViewModel : ViewModel() {
    private val _alarms = MutableStateFlow<List<AlarmItem>>(emptyList())
    val alarms: StateFlow<List<AlarmItem>> = _alarms

    fun addAlarm(hour: Int, minute: Int) {
        val id = System.currentTimeMillis()
        _alarms.update { it + AlarmItem(id = id, hour = hour, minute = minute) }
    }

    fun toggleAlarm(id: Long, enabled: Boolean) {
        _alarms.update { list -> list.map { if (it.id == id) it.copy(enabled = enabled) else it } }
    }

    fun deleteAlarm(id: Long) {
        _alarms.update { list -> list.filterNot { it.id == id } }
    }
}
