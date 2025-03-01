package com.example.cs3318_mobile_app_project

data class EventModel(
    var eventName: String,
    var startTime: String,
    var endTime: String,
    var recurrence: String,
    var location: String,
    var reminder: String
)