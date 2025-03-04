package com.example.cs3318_mobile_app_project

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class ScheduleEventsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schedule_events)

        val startDateEditText: EditText = findViewById(R.id.startDateEditText)
        val endDateEditText: EditText = findViewById(R.id.endDateEditText)
        val startDateIcon: ImageView = findViewById(R.id.startDateIcon)
        val endDateIcon: ImageView = findViewById(R.id.endDateIcon)
        val classScheduleContainer: LinearLayout = findViewById(R.id.classScheduleContainer)
        val addClassButton: Button = findViewById(R.id.addClassButton)
        val btnSendToCalendar: Button = findViewById(R.id.btnSendToCalendar)

        // Open Date Picker when clicking on EditText or Calendar Icon
        val dateClickListener = { editText: EditText -> showDatePicker(editText) }
        startDateEditText.setOnClickListener { dateClickListener(startDateEditText) }
        startDateIcon.setOnClickListener { dateClickListener(startDateEditText) }
        endDateEditText.setOnClickListener { dateClickListener(endDateEditText) }
        endDateIcon.setOnClickListener { dateClickListener(endDateEditText) }

        // Add class dynamically when button is clicked
        addClassButton.setOnClickListener {
            addClassSchedule(classScheduleContainer)
        }

        // Send calendar data to Google Calendar
        btnSendToCalendar.setOnClickListener {
            // Implement Google Calendar integration here
        }
    }

    private fun showDatePicker(editText: EditText) {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDay ->
            val selectedDate = "${selectedMonth + 1}/$selectedDay/$selectedYear"
            editText.setText(selectedDate)
        }, year, month, day)

        datePickerDialog.show()
    }

    private fun showTimePicker(editText: EditText) {
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        val timePickerDialog = TimePickerDialog(this, { _, selectedHour, selectedMinute ->
            val formattedTime = String.format("%02d:%02d", selectedHour, selectedMinute)
            editText.setText(formattedTime)
        }, hour, minute, false)

        timePickerDialog.show()
    }

    private fun addClassSchedule(container: LinearLayout) {
        val classView = LayoutInflater.from(this).inflate(R.layout.class_time_entry, container, false)

        val classNameEditText: EditText = classView.findViewById(R.id.classNameEditText)
        val startTimeEditText: EditText = classView.findViewById(R.id.classStartTimeEditText)
        val endTimeEditText: EditText = classView.findViewById(R.id.classEndTimeEditText)
        val startTimeIcon: ImageView = classView.findViewById(R.id.classStartTimeIcon)
        val endTimeIcon: ImageView = classView.findViewById(R.id.classEndTimeIcon)
        val locationEditText: EditText = classView.findViewById(R.id.classLocationEditText)
        val reminderEditText: EditText = classView.findViewById(R.id.classReminderEditText)

        val timeClickListener = { editText: EditText -> showTimePicker(editText) }
        startTimeEditText.setOnClickListener { timeClickListener(startTimeEditText) }
        startTimeIcon.setOnClickListener { timeClickListener(startTimeEditText) }
        endTimeEditText.setOnClickListener { timeClickListener(endTimeEditText) }
        endTimeIcon.setOnClickListener { timeClickListener(endTimeEditText) }

        container.addView(classView)
    }
}