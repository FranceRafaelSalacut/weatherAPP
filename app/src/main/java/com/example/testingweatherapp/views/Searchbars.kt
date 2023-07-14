package com.example.testingweatherapp.views

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testingweatherapp.Interface.Contract
import com.example.testingweatherapp.R

class Searchbars: AppCompatActivity(), Contract.View{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search_activity)

        val location_result = findViewById<View>(R.id.search_results) as RecyclerView

        val locations = arrayOf("Cebu", "Bohol", "Dumagetme", "Argao", "DD", "Dionn", "Panpan", "Mingky", "Caturay", "Josephine")

        val adapter = CustomAdapter(locations)

        location_result.adapter = adapter

        location_result.layoutManager = LinearLayoutManager(this)
    }



    override fun getdata(): List<Any> {
        TODO("Not yet implemented")
    }

    override fun display(display: List<Any>?) {
        TODO("Not yet implemented")
    }
}