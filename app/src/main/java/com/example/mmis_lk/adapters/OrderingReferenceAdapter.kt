package com.example.mmis_lk.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mmis_lk.R
import com.example.mmis_lk.retrofit.models.OrderingReference

class OrderingReferenceAdapter(private val dataSet: Array<OrderingReference>) :
    RecyclerView.Adapter<OrderingReferenceAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        // Define click listener for the ViewHolder's View
        val referenceName: TextView = view.findViewById(R.id.textViewReferenceName)
        val referenceStatus: Button = view.findViewById(R.id.buttonForReferenceStatus)
        val referenceDate: TextView = view.findViewById(R.id.textViewReferenceDate)
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val itemView = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_reference, viewGroup, false)

        return ViewHolder(itemView)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.referenceName.text = dataSet[position].reference.name
        viewHolder.referenceDate.text = dataSet[position].date.toString()
        viewHolder.referenceStatus.text = dataSet[position].status.name
//        if (dataSet[position].status.id == 1){
//            viewHolder.referenceStatus.setBackgroundResource(R.drawable.button_green)
//        }
//        if (dataSet[position].status.id == 2){
//            viewHolder.referenceStatus.setBackgroundResource(R.drawable.button_red)
//        }
//        if (dataSet[position].status.id == 3){
//            viewHolder.referenceStatus.setBackgroundResource(R.drawable.button_orange)
//        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}