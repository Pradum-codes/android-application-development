package com.pradumcodes.attendance.data.local.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "subjects")
data class Subject(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val delivered: Int = 0,
    val attended: Int = 0,
    val totalExpected: Int
)

fun Subject.attendancePercentage(): Float =
    if (delivered == 0) 0f
    else (attended * 100f) / delivered
