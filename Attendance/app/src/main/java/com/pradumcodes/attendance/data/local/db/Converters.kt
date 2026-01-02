package com.pradumcodes.attendance.data.local.db

import androidx.room.TypeConverter
import com.pradumcodes.attendance.domain.model.AttendanceStatus

class Converters {

    @TypeConverter
    fun fromStatus(status: AttendanceStatus): String = status.name

    @TypeConverter
    fun toStatus(value: String): AttendanceStatus =
        AttendanceStatus.valueOf(value)
}
