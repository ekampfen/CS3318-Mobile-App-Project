package com.example.cs3318_mobile_app_project

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView

class EventAdapter(private val events: MutableList<EventModel>) :
    RecyclerView.Adapter<EventAdapter.EventViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_event, parent, false)
        return EventViewHolder(view)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val event = events[position]
        holder.etEventName.setText(event.eventName)
        holder.etStartTime.setText(event.startTime)
        holder.etEndTime.setText(event.endTime)
        holder.etLocation.setText(event.location)
        holder.etReminder.setText(event.reminder)
    }

    override fun getItemCount(): Int {
        return events.size
    }

    class EventViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val etEventName: EditText = itemView.findViewById(R.id.etEventName)
        val etStartTime: EditText = itemView.findViewById(R.id.etStartTime)
        val etEndTime: EditText = itemView.findViewById(R.id.etEndTime)
        val etLocation: EditText = itemView.findViewById(R.id.etLocation)
        val etReminder: EditText = itemView.findViewById(R.id.etReminder)
    }
}