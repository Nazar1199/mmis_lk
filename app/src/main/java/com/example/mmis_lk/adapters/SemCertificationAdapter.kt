package com.example.mmis_lk.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mmis_lk.R
import com.example.mmis_lk.retrofit.models.Certification

class SemCertificationAdapter(private val dataSet: Array<Array<Certification>>) :
    RecyclerView.Adapter<SemCertificationAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        // Define click listener for the ViewHolder's View
        var semesterRecyclerView: RecyclerView = view.findViewById(R.id.semesterRecyclerView)
        val semesterBT: Button = view.findViewById(R.id.buttonSemester)
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val itemView = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_certification_sem, viewGroup, false)
        return ViewHolder(itemView)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.semesterBT.text = "Семестр " + dataSet[position][0].periodSem
        viewHolder.semesterRecyclerView.layoutManager = LinearLayoutManager(viewHolder.semesterRecyclerView.context  , LinearLayoutManager.VERTICAL, false)
        dataSet[position].sortBy { it.typeControl.id }
        dataSet[position].reverse()
        viewHolder.semesterRecyclerView.adapter = CertificationAdapter(dataSet[position])
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}