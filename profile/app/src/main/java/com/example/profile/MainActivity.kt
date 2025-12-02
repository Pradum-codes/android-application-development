package com.example.profile

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val nameInp: EditText = findViewById(R.id.nameInp)
        val emailInp: EditText = findViewById(R.id.emailInp)
        val phoneInp: EditText = findViewById(R.id.editTextPhone)
        val passInp: EditText = findViewById(R.id.passwordInp)
        val repassInp: EditText = findViewById(R.id.repeatPasswordInp)

        // Checkbox
        val checkCse211: CheckBox = findViewById(R.id.cse211)
        val checkInt363: CheckBox = findViewById(R.id.int363)
        val checkCse322: CheckBox = findViewById(R.id.cse322)

        val rGrp: RadioGroup = findViewById(R.id.genderGroup)
        val btn: Button = findViewById(R.id.submitBtn)
        val resetBtn: Button = findViewById(R.id.resetBtn)

        val viewResult: TextView = findViewById(R.id.resultView)

        btn.setOnClickListener {
            var courses = StringBuilder("")
            if (checkCse211.isChecked) courses.append("CSE211 ")
            if (checkInt363.isChecked) courses.append("INT363 ")
            if (checkCse322.isChecked) courses.append("CSE322 ")

            val selId = rGrp.checkedRadioButtonId
            if (selId == -1) {
                Toast.makeText(applicationContext, "Please select a gender", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (nameInp.text.toString().isEmpty()
                || emailInp.text.toString().isEmpty()
                || phoneInp.text.toString().isEmpty()
                || passInp.text.toString().isEmpty()
                || repassInp.text.toString().isEmpty()
            ) {
                Toast.makeText(applicationContext, "All fields are required", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (passInp.text.toString() != repassInp.text.toString()) {
                Toast.makeText(applicationContext, "Passwords do not match", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val rBtn: RadioButton = findViewById(selId)

            val result = """
                Name: ${nameInp.text}
                Email: ${emailInp.text}
                Phone: ${phoneInp.text}
                Gender: ${rBtn.text}                
                Courses: $courses
            """.trimIndent()

            viewResult.text = result
            Toast.makeText(applicationContext, "Form Submitted", Toast.LENGTH_SHORT).show()
        }

        resetBtn.setOnClickListener {
            rGrp.clearCheck()

            checkCse211.isChecked = false
            checkInt363.isChecked = false
            checkCse322.isChecked = false

            nameInp.text.clear()
            emailInp.text.clear()
            phoneInp.text.clear()
            passInp.text.clear()
            repassInp.text.clear()

            viewResult.text = ""

            Toast.makeText(applicationContext, "Form reset", Toast.LENGTH_SHORT).show()
        }
    }
}
