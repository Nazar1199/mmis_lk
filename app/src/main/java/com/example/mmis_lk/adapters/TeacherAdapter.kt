package com.example.mmis_lk.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mmis_lk.R
import com.example.mmis_lk.retrofit.models.Teacher
import com.squareup.picasso.Picasso

class TeacherAdapter(private val dataSet: Array<Teacher>) :
    RecyclerView.Adapter<TeacherAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        // Define click listener for the ViewHolder's View
        val teacherName: TextView = view.findViewById(R.id.textViewTeacherItemName)
        val teacherAvatar: ImageView = view.findViewById(R.id.imageViewTeacherItemAvatar)
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val itemView = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_teacher, viewGroup, false)

        return ViewHolder(itemView)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.teacherName.text = dataSet[position].lastName + " " + dataSet[position].firstName + " " + dataSet[position].patronymic
        Picasso.get()
            .load(dataSet[position].photo)
            .into(viewHolder.teacherAvatar)
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}