package com.pradumcodes.attendance.ui.subject

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pradumcodes.attendance.data.local.db.entities.Subject
import com.pradumcodes.attendance.data.repository.AttendanceRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class SubjectViewModel(
    private val repository: AttendanceRepository
) : ViewModel() {

    val subjects = repository.getSubjects()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )

    fun addSubject(name: String, totalExpected: Int) {
        viewModelScope.launch {
            repository.addSubject(name, totalExpected)
        }
    }
}
