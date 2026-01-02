package com.pradumcodes.attendance.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.pradumcodes.attendance.data.local.db.entities.Subject
import kotlinx.coroutines.flow.Flow

@Dao
interface SubjectDao {

    @Insert
    suspend fun insert(subject: Subject): Long

    @Query("SELECT * FROM subjects")
    fun getAllSubjects(): Flow<List<Subject>>

    @Query("""
        UPDATE subjects
        SET 
            delivered = delivered + 1,
            attended = attended + :attendedIncrement
        WHERE id = :subjectId
    """)
    suspend fun updateAttendanceCounters(
        subjectId: Long,
        attendedIncrement: Int
    )
}

