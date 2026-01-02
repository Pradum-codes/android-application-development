package com.pradumcodes.attendance.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.pradumcodes.attendance.data.local.dao.AttendanceDao
import com.pradumcodes.attendance.data.local.dao.SubjectDao
import com.pradumcodes.attendance.data.local.db.entities.Attendance
import com.pradumcodes.attendance.data.local.db.entities.Subject


@Database(
    entities = [Subject::class, Attendance::class],
    version = 2,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AttendanceDatabase : RoomDatabase() {

    abstract fun subjectDao(): SubjectDao
    abstract fun attendanceDao(): AttendanceDao

    companion object {
        @Volatile
        private var INSTANCE: AttendanceDatabase? = null

        fun getInstance(context: Context): AttendanceDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                                context.applicationContext,
                                AttendanceDatabase::class.java,
                                "attendance_db"
                            ).fallbackToDestructiveMigration(true).build().also { INSTANCE = it }
            }
        }
    }
}
