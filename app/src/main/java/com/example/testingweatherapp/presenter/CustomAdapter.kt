package com.example.testingweatherapp.presenter

import android.app.SearchManager.OnCancelListener
import android.content.Intent
import android.util.Log
import android.view.Display.Mode
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.example.testingweatherapp.Interface.Contract
import com.example.testingweatherapp.R
import com.example.testingweatherapp.views.dummclass


class CustomAdapter(private val dataSet: List<String>, private var model: Contract.Model) :
    RecyclerView.Adapter<CustomAdapter.ViewHolder>() {
    private var onClickListener: OnClickListener? = null

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView
        val forecast: ImageView
        init {
            // Define click listener for the ViewHolder's View
            textView = view.findViewById(R.id.location_text)
            forecast = view.findViewById(R.id.location_forecast)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.search_option, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.textView.text = dataSet[position]
        model.getIconNEW(dataSet[position], viewHolder.forecast)

        viewHolder.itemView.setOnClickListener {
            if (onClickListener != null) {
                onClickListener!!.onClick(dataSet[position])
            }
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

    // A function to bind the onclickListener.
    fun setOnClickListener(onClickListener: OnClickListener) {
        this.onClickListener = onClickListener
    }

    // onClickListener Interface
    interface OnClickListener {
        fun onClick(location: String)
    }

}
