package com.example.mmis_lk.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.mmis_lk.R
import com.example.mmis_lk.retrofit.models.Auditorium

class TimetableAuditoriumFilterAdapter(private val dataSet: Array<Auditorium>) :
    RecyclerView.Adapter<TimetableAuditoriumFilterAdapter.ViewHolder>() {
    var onClickListener: ((Auditorium) -> Unit)? = null

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        // Define click listener for the ViewHolder's View
        val title: Button = view.findViewById(R.id.buttonTitle)
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val itemView = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_tametable_filter, viewGroup, false)
        return ViewHolder(itemView)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.title.text = dataSet[position].name
        viewHolder.title.setOnClickListener {
            onClickListener?.invoke(dataSet[position])
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

    // A function to bind the onclickListener.
    fun setOnClickListener(onClickListener: OnClickListener) {
        this.onClickListener = onClickListener
    }

    // onClickListener Interface
    interface OnClickListener : (Auditorium) -> Unit {
        fun onClick(position: Int, model: Auditorium)
    }
}