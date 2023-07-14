package com.example.testingweatherapp.views

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testingweatherapp.Interface.Contract
import com.example.testingweatherapp.R
import com.example.testingweatherapp.models.Model
import com.example.testingweatherapp.presenter.presenter

class Searchbars: AppCompatActivity(), Contract.View{

    var presenter: presenter? = null
    private var SearchButton: ConstraintLayout? = null
    private var BackButton: ConstraintLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search_activity)

        val location_result = findViewById<View>(R.id.search_results) as RecyclerView

        val locations = arrayOf("Cebu", "Bohol", "Dumagetme", "Argao", "DD", "Dionn", "Panpan", "Mingky", "Caturay", "Josephine")

        val adapter = CustomAdapter(locations)

        location_result.adapter = adapter

        location_result.layoutManager = LinearLayoutManager(this)

        presenter = presenter(this, Model())
        SearchButton = findViewById<ConstraintLayout>(R.id.search_icon)
        BackButton = findViewById<ConstraintLayout>(R.id.back_button)

        this.SearchButton!!.setOnClickListener(View.OnClickListener {
            Log.d("this", "looking for some places?")
            presenter!!.onButtonClikcSearch()
        })

        this.BackButton!!.setOnClickListener(View.OnClickListener {
            Log.d("this", "Going back downtown")
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        })
    }



    override fun getdata(): List<Any> {
        return listOf("Manila")
    }

    override fun display(display: List<Any>?) {
        TODO("Not yet implemented")
    }
}