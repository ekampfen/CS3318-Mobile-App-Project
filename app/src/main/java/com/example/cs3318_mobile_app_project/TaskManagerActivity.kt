package com.example.cs3318_mobile_app_project

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.*
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import java.text.SimpleDateFormat
import java.util.*

class TaskManagerActivity : AppCompatActivity() {
    private val viewModel: TaskViewModel by viewModels()
    private val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_manager)

        val titleInput = findViewById<EditText>(R.id.titleInput)
        val descriptionInput = findViewById<EditText>(R.id.descriptionInput)
        val categorySpinner = findViewById<Spinner>(R.id.categorySpinner)
        val prioritySpinner = findViewById<Spinner>(R.id.prioritySpinner)
        val dateButton = findViewById<Button>(R.id.dateButton)
        val addTaskButton = findViewById<Button>(R.id.addTaskButton)
        val taskListView = findViewById<ListView>(R.id.taskListView)

        val categoryAdapter = ArrayAdapter.createFromResource(
            this, R.array.task_categories, android.R.layout.simple_spinner_item
        )
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        categorySpinner.adapter = categoryAdapter

        val priorityAdapter = ArrayAdapter.createFromResource(
            this, R.array.task_priorities, android.R.layout.simple_spinner_item
        )
        priorityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        prioritySpinner.adapter = priorityAdapter

        val taskListAdapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mutableListOf())
        taskListView.adapter = taskListAdapter

        var selectedDate: String = ""
        dateButton.setOnClickListener {
            val calendar = Calendar.getInstance()
            DatePickerDialog(this, { _, year, month, day ->
                TimePickerDialog(this, { _, hour, minute ->
                    calendar.set(year, month, day, hour, minute)
                    selectedDate = dateFormat.format(calendar.time)
                    dateButton.text = selectedDate
                }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true).show()
            }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show()
        }

        viewModel.tasks.observe(this, Observer { tasks ->
            taskListAdapter.clear()
            taskListAdapter.addAll(tasks.map { "${it.title} - ${it.priority} - ${it.date}" })
        })

        addTaskButton.setOnClickListener {
            val title = titleInput.text.toString()
            val description = descriptionInput.text.toString()
            val category = categorySpinner.selectedItem.toString()
            val priority = prioritySpinner.selectedItem.toString()
            if (title.isNotEmpty() && description.isNotEmpty() && selectedDate.isNotEmpty()) {
                viewModel.addTask(title, description, category, priority, selectedDate)
                titleInput.text.clear()
                descriptionInput.text.clear()
                dateButton.text = "Select Date & Time"
            }
        }

        taskListView.setOnItemClickListener { _, _, position, _ ->
            val task = viewModel.tasks.value?.get(position)
            task?.let {
                Toast.makeText(this, "Task: ${it.title}\nDescription: ${it.description}\nCategory: ${it.category}\nPriority: ${it.priority}\nDate: ${it.date}", Toast.LENGTH_LONG).show()
            }
        }
    }
}