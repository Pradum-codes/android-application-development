package com.pradumcodes.attendance.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.pradumcodes.attendance.data.local.db.entities.Attendance
import kotlinx.coroutines.flow.Flow

@Dao
interface AttendanceDao {

    @Insert
    suspend fun insert(attendance: Attendance)

    @Query("""
        SELECT * FROM attendance
        WHERE subjectId = :subjectId
        ORDER BY date DESC
    """)
    fun getAttendanceForSubject(subjectId: Long): Flow<List<Attendance>>
}

