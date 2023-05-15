package com.example.mmis_lk.adapters

import android.icu.text.SimpleDateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mmis_lk.R
import com.example.mmis_lk.retrofit.models.Certification

class CertificationAdapter(private val dataSet: Array<Certification>) :
    RecyclerView.Adapter<CertificationAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        // Define click listener for the ViewHolder's View
        val disciplineName: TextView = view.findViewById(R.id.textViewDisciplineName)
        val markDate: TextView = view.findViewById(R.id.textViewMarkDate)
        val markName: TextView = view.findViewById(R.id.textViewMark)
        val hours: TextView = view.findViewById(R.id.textViewHours)
        val teacher: TextView = view.findViewById(R.id.textViewTeacher)
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val itemView = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_certification, viewGroup, false)

        return ViewHolder(itemView)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        var dateFormater: SimpleDateFormat = SimpleDateFormat("dd.MM.yyyy")
        viewHolder.disciplineName.text = dataSet[position].discipline.name
        if (dataSet[position].mark == 1 || dataSet[position].mark == 2) {
            viewHolder.markName.text = "Неуд"
        } else {
            if (dataSet[position].typeControl.id == 1) {
                if (dataSet[position].mark == 3) {
                    viewHolder.markName.text = "Удовл"
                } else if (dataSet[position].mark == 4) {
                    viewHolder.markName.text = "Хор"
                } else if (dataSet[position].mark == 5) {
                    viewHolder.markName.text = "Отл"
                }
            } else {
                viewHolder.markName.text = "Зачет"
            }
        }
        viewHolder.markDate.text = dateFormater.format(dataSet[position].dateTime)
        viewHolder.hours.text = dataSet[position].numberOfHours.toString() + " ч."
        viewHolder.teacher.text =
                dataSet[position].teacher.lastName + " " +
                dataSet[position].teacher.firstName + " " +
                dataSet[position].teacher.patronymic
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}