package com.pradumcodes.attendance

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.compose.BackHandler
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.pradumcodes.attendance.ui.subject.SubjectScreen
import com.pradumcodes.attendance.ui.subject.SubjectViewModel
import com.pradumcodes.attendance.ui.summary.SummaryViewModel
import com.pradumcodes.attendance.ui.summary.SummaryScreen
import com.pradumcodes.attendance.data.local.db.entities.Subject

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val app = application as AttendanceApplication
        val repository = app.repository

        setContent {
            val subjectVm = remember {
                SubjectViewModel(repository)
            }

            var selectedSubject by remember { mutableStateOf<Subject?>(null) }

            LaunchedEffect(Unit) {
                subjectVm.addSubject("CSE", 15)
                subjectVm.addSubject("Maths", 15)
                subjectVm.addSubject("Physics", 15)
                subjectVm.addSubject("Operating Systems", 15)
                subjectVm.addSubject("Computer Networks", 15)
            }

            if (selectedSubject == null) {
                SubjectScreen(
                    viewModel = subjectVm,
                    onSubjectClick = { subject ->
                        selectedSubject = subject
                    }
                )
            } else {
                // Back clears selection and returns to subject list
                BackHandler {
                    selectedSubject = null
                }

                // remember the SummaryViewModel for the selected subject
                val summaryVm = remember(selectedSubject) {
                    SummaryViewModel(repository, selectedSubject!!)
                }

                SummaryScreen(viewModel = summaryVm)
            }
        }
    }
}
