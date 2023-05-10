package com.example.mmis_lk.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mmis_lk.R
import com.example.mmis_lk.retrofit.models.TimeTable

class DayTimetableAdapter(private val dataSet: Array<Array<TimeTable>>) :
    RecyclerView.Adapter<DayTimetableAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        // Define click listener for the ViewHolder's View
        var lessonsRecyclerView: RecyclerView = view.findViewById(R.id.lessonsRecyclerView)
        val dateBt: Button = view.findViewById(R.id.buttonDate)
        val dateDayBt: Button = view.findViewById(R.id.buttonDayOfDate)
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val itemView = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_timetable_day, viewGroup, false)
        return ViewHolder(itemView)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.dateBt.text = dataSet[position][0].date.toString()
        viewHolder.lessonsRecyclerView.layoutManager = LinearLayoutManager(viewHolder.lessonsRecyclerView.context  , LinearLayoutManager.VERTICAL, false)
//        val ar: Array<TimeTable> = dataSet[position].sortBy { it.lessonTime.number }
        viewHolder.lessonsRecyclerView.adapter = LessonTimetableAdapter(dataSet[position])
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}