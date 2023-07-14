package com.example.testingweatherapp.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.example.testingweatherapp.Interface.Contract
import com.example.testingweatherapp.R
import com.example.testingweatherapp.models.Model
import com.example.testingweatherapp.presenter.presenter
import coil.load
import coil.transform.CircleCropTransformation

class Forecast : AppCompatActivity(), Contract.View{
    private var button: Button? = null
    var presenter: presenter? = null
    private var location: AutoCompleteTextView? = null
    private var dips: TextView? = null
    private var progressbar: ProgressBar? = null
    private var forecast_icon: ImageView?  = null
    private var back: Button? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.forecast)

        dips = findViewById<TextView>(R.id.disp)
        location = findViewById<AutoCompleteTextView>(R.id.location)
        progressbar = findViewById<ProgressBar>(R.id.progressBar)
        forecast_icon = findViewById<ImageView>(R.id.forecast_icon)
        back = findViewById<Button>(R.id.back_button)

        //val location = "Cebu"
        //val url = "http://api.weatherapi.com/v1/forecast.json?key=2ebf9aea9a1e493ca2b20050230707&q=Manila&days=1&aqi=no&alerts=no"


        button = findViewById<Button>(R.id.buttonn)
        presenter = presenter(this, Model())


        this.button!!.setOnClickListener(View.OnClickListener {
            //Log.d("this", "im loggin from main ACT")
            presenter!!.onButtonClickForecast()
        })

        this.back!!.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, MainActivity::class.java )
            startActivity(intent)
        })
    }

    override fun getdata(): List<Any> {
       /* val API_KEY = "2ebf9aea9a1e493ca2b20050230707"
        val call = "forecast"
        val text_location = "Cebu"
        Log.d("This", "You are inside contructURL"*//* location!!.text.toString()*//*)
        val url = "http://api.weatherapi.com/v1/$call.json?key=${API_KEY}&q=$text_location"*/

        progressbar!!.visibility = View.VISIBLE
        /*forecast_icon!!.visibility = View.INVISIBLE*/
        dips!!.visibility = View.INVISIBLE

        val location = location!!.text.toString()
        val days = 1
        return listOf(location, days)
    }

    override fun display(location_name: String, location_region: String, forecast: String, icon_url: Any) {
        dips!!.text = "$forecast\n$location_name, $location_region"
        forecast_icon!!.load("https:$icon_url"){
            crossfade(true)
            placeholder(R.drawable.image_temp)
            transformations(CircleCropTransformation())
        }
        progressbar!!.visibility = View.INVISIBLE
        /*forecast_icon!!.visibility = View.VISIBLE*/
        dips!!.visibility = View.VISIBLE
    }

}