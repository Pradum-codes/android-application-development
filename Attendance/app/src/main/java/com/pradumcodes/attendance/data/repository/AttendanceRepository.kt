package com.pradumcodes.attendance.data.repository

import androidx.room.withTransaction
import com.pradumcodes.attendance.data.local.dao.AttendanceDao
import com.pradumcodes.attendance.data.local.dao.SubjectDao
import com.pradumcodes.attendance.data.local.db.AttendanceDatabase
import com.pradumcodes.attendance.data.local.db.entities.Attendance
import com.pradumcodes.attendance.data.local.db.entities.Subject
import com.pradumcodes.attendance.domain.model.AttendanceStatus
import com.pradumcodes.attendance.domain.model.AttendanceSummary

class AttendanceRepository(
    private val subjectDao: SubjectDao,
    private val attendanceDao: AttendanceDao,
    private val database: AttendanceDatabase
) {

    fun getSubjects() = subjectDao.getAllSubjects()

    suspend fun addSubject(
        name: String,
        totalExpected: Int
    ) {
        subjectDao.insert(
            Subject(
                name = name,
                totalExpected = totalExpected
            )
        )
    }

    suspend fun markAttendance(
        subjectId: Long,
        date: Long,
        status: AttendanceStatus
    ) {
        database.withTransaction {

            // 1. Insert attendance event
            attendanceDao.insert(
                Attendance(
                    subjectId = subjectId,
                    date = date,
                    status = status
                )
            )

            // 2. Update subject aggregates
            val attendedInc =
                if (status == AttendanceStatus.PRESENT) 1 else 0

            subjectDao.updateAttendanceCounters(
                subjectId = subjectId,
                attendedIncrement = attendedInc
            )
        }
    }

    suspend fun getSummary(subject: Subject): AttendanceSummary {
        return AttendanceSummary(
            delivered = subject.delivered,
            attended = subject.attended,
            totalExpected = subject.totalExpected
        )
    }

    suspend fun getCurrentPercentAge(subject: Subject): Float {
        return if (subject.delivered == 0) {
            0f
        } else {
            (subject.attended * 100f) / subject.delivered
        }
    }

}
