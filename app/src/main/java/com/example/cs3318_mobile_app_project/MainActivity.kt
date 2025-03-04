package com.example.cs3318_mobile_app_project

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnScheduleEvents: Button = findViewById(R.id.btnScheduleEvents)
        val btnTaskManager: Button = findViewById(R.id.btnTaskManager)

        btnScheduleEvents.setOnClickListener {
            val intent = Intent(this, ScheduleEventsActivity::class.java)
            startActivity(intent)
        }

        btnTaskManager.setOnClickListener {
            val intent = Intent(this, TaskManagerActivity::class.java)
            startActivity(intent)
        }
    }
}