package com.example.cs3318_mobile_app_project

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

data class Task(
    val title: String,
    val description: String,
    val category: String,
    val priority: String,
    val date: String
)

class TaskViewModel : ViewModel() {
    private val _tasks = MutableLiveData<MutableList<Task>>(mutableListOf())
    val tasks: LiveData<MutableList<Task>> = _tasks

    fun addTask(title: String, description: String, category: String, priority: String, date: String) {
        val updatedList = _tasks.value ?: mutableListOf()
        updatedList.add(Task(title, description, category, priority, date))
        _tasks.value = updatedList
    }

    fun removeTask(task: Task) {
        val updatedList = _tasks.value ?: mutableListOf()
        updatedList.remove(task)
        _tasks.value = updatedList
    }
}