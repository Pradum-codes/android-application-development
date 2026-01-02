package com.pradumcodes.attendance

import android.app.Application
import com.pradumcodes.attendance.data.local.db.AttendanceDatabase
import com.pradumcodes.attendance.data.repository.AttendanceRepository

class AttendanceApplication : Application() {

    val database by lazy {
        AttendanceDatabase.getInstance(this)
    }

    val repository by lazy {
        AttendanceRepository(
            database.subjectDao(),
            database.attendanceDao(),
            database
        )
    }
}
