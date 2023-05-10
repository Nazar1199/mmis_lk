package com.example.mmis_lk.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mmis_lk.R
import com.example.mmis_lk.retrofit.models.OrderingReference
import com.example.mmis_lk.retrofit.models.TimeTable

class LessonTimetableAdapter(private val dataSet: Array<TimeTable>) :
    RecyclerView.Adapter<LessonTimetableAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        // Define click listener for the ViewHolder's View
        val disciplineName: TextView = view.findViewById(R.id.textViewDisciplineName)
        val group: TextView = view.findViewById(R.id.textViewGroup)
        val teacher: TextView = view.findViewById(R.id.textViewTeacher)
        val lessonTime: Button = view.findViewById(R.id.buttonLessonTime)
        val typeAud: Button = view.findViewById(R.id.buttonLessonTypeAud)
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val itemView = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_lesson, viewGroup, false)

        return ViewHolder(itemView)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.disciplineName.text = dataSet[position].discipline.name
        viewHolder.group.text = dataSet[position].group.name
        viewHolder.teacher.text = dataSet[position].teacher.lastName + " " +
                dataSet[position].teacher.firstName + " " +
                dataSet[position].teacher.patronymic
        viewHolder.lessonTime.text = dataSet[position].lessonTime.startTime + " " +
                dataSet[position].lessonTime.endTime
        viewHolder.typeAud.text = dataSet[position].lessonType.abbr + " " +
                dataSet[position].auditorium.name
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}