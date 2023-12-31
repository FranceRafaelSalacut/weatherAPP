package com.example.testingweatherapp.views

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.INVISIBLE
import com.example.testingweatherapp.Interface.Contract
import com.example.testingweatherapp.R
import com.example.testingweatherapp.models.Model
import com.example.testingweatherapp.presenter.CustomAdapter
import com.example.testingweatherapp.presenter.presenter

class Searchbars: AppCompatActivity(), Contract.View{

    var presenter: presenter? = null
    private var SearchButton: ConstraintLayout? = null
    private var BackButton: ConstraintLayout? = null
    private var location_result: RecyclerView? = null
    private var search_data: EditText? = null
    private var progressbar: ProgressBar? = null
    private var no_data: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search_activity)

        presenter = presenter(this, Model())
        SearchButton = findViewById<ConstraintLayout>(R.id.search_icon)
        BackButton = findViewById<ConstraintLayout>(R.id.back_button)
        location_result = findViewById<RecyclerView>(R.id.search_results)
        search_data = findViewById<EditText>(R.id.search_bar)
        progressbar = findViewById<ProgressBar>(R.id.progressBar)
        no_data = findViewById<TextView>(R.id.no_data)

        /*this.SearchButton!!.setOnClickListener(View.OnClickListener {
            Log.d("this", "looking for some places?")
            presenter!!.onButtonClick(from = "search")
        })*/
        this.search_data!!.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                Log.d("list", "after")
                presenter!!.onButtonClick(from="search")
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                Log.d("list", "on")
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                Log.d("list", "before")
            }
        })


        this.BackButton!!.setOnClickListener(View.OnClickListener {
            Log.d("this", "Going back downtown")
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        })
    }



    override fun getdata(): List<Any> {
        showProgressBar()
        return listOf(search_data!!.text)
    }

    override fun display(display: List<Any>?) {

        showNoData()

        if(display!=null && display.size>0) {
            val display = display as List<String>
            val adapter = CustomAdapter(display, Model())
            location_result!!.swapAdapter(adapter, false)
            location_result!!.layoutManager = LinearLayoutManager(this)

            adapter.setOnClickListener(object : CustomAdapter.OnClickListener {
                override fun onClick(location: String) {
                    val intent = Intent(this@Searchbars, dummclass::class.java)
                    intent.putExtra("Location", location)
                    startActivity(intent)
                }
            })
            showData()
        }

    }

    fun showProgressBar(){
        no_data!!.visibility = View.INVISIBLE
        progressbar!!.visibility = View.VISIBLE
        location_result!!.visibility = View.INVISIBLE
    }

    fun showNoData() {
        no_data!!.visibility = View.VISIBLE
        progressbar!!.visibility = View.INVISIBLE
        location_result!!.visibility = INVISIBLE
    }

    fun showData(){
        no_data!!.visibility = View.INVISIBLE
        progressbar!!.visibility = View.INVISIBLE
        location_result!!.visibility = View.VISIBLE
    }


}