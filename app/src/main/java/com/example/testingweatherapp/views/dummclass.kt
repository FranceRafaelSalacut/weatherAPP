package com.example.testingweatherapp.views

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.example.testingweatherapp.Interface.Contract
import com.example.testingweatherapp.R
import com.example.testingweatherapp.models.Model
import com.example.testingweatherapp.presenter.presenter

class dummclass : AppCompatActivity(), Contract.View {

    private var button: Button? = null
    var presenter: presenter? = null
    private var location: AutoCompleteTextView? = null
    private var dips: TextView? = null
    private var progressbar: ProgressBar? = null
    private var forecast_icon: ImageView?  = null
    private var back: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.forecast_other)

        //Assigning values to the declared private vals
        dips = findViewById<TextView>(R.id.disp)
        progressbar = findViewById<ProgressBar>(R.id.progressBar)
        forecast_icon = findViewById<ImageView>(R.id.forecast_icon)
        back = findViewById<Button>(R.id.back_button)
        button = findViewById<Button>(R.id.buttonn)
        presenter = presenter(this, Model())

        presenter!!.onButtonClick(from ="forecast")

        this.back!!.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, Searchbars::class.java )
            startActivity(intent)
        })

    }

    override fun getdata(): List<Any> {

        progressbar!!.visibility = View.VISIBLE
        dips!!.visibility = View.INVISIBLE

        val location:String = intent.getStringExtra("Location")?:""
        val days = 1
        return listOf(location, days)
    }

    override fun display(display: List<Any>?) {

        val to_display:String
        val icon_url:String

        when (display){
            null -> {
                to_display = "No Data\nNo Data, No Data"
                icon_url = ""
            }
            else -> {
                to_display = "${display[2]}\n${display[0]},${display[1]}"
                icon_url = "https:${display[3]}"
            }
        }

        dips!!.text = to_display

        forecast_icon!!.load(icon_url){
            crossfade(true)
            error(R.drawable.sunny_icon)
            placeholder(R.drawable.image_temp)
            transformations(CircleCropTransformation())
        }
        progressbar!!.visibility = View.INVISIBLE
        dips!!.visibility = View.VISIBLE
    }

}


