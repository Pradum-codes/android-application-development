package com.pradumcodes.attendance.ui.summary

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pradumcodes.attendance.data.local.db.entities.Subject
import com.pradumcodes.attendance.data.repository.AttendanceRepository
import com.pradumcodes.attendance.domain.model.AttendanceStatus
import com.pradumcodes.attendance.domain.model.AttendanceSummary
import kotlinx.coroutines.launch

class SummaryViewModel(
    private val repository: AttendanceRepository,
    private val subject: Subject
) : ViewModel() {

    private val _summary = mutableStateOf<AttendanceSummary?>(null)
    val summary: State<AttendanceSummary?> = _summary

    fun loadSummary() {
        viewModelScope.launch {
            _summary.value = repository.getSummary(subject)
        }
    }

    fun markPresent() {
        mark(true)
    }

    fun markAbsent() {
        mark(false)
    }

    private fun mark(attended: Boolean) {
        viewModelScope.launch {
            repository.markAttendance(
                subjectId = subject.id,
                date = System.currentTimeMillis(),
                status = if (attended)
                    AttendanceStatus.PRESENT
                else
                    AttendanceStatus.ABSENT
            )
            loadSummary()
        }
    }
}
