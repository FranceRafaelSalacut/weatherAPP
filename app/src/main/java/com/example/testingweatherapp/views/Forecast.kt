package com.example.testingweatherapp.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Display
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
import kotlinx.coroutines.DelicateCoroutinesApi

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

        //Assigning values to the declared private vals
        dips = findViewById<TextView>(R.id.disp)
        location = findViewById<AutoCompleteTextView>(R.id.location)
        progressbar = findViewById<ProgressBar>(R.id.progressBar)
        forecast_icon = findViewById<ImageView>(R.id.forecast_icon)
        back = findViewById<Button>(R.id.back_button)
        button = findViewById<Button>(R.id.buttonn)
        presenter = presenter(this, Model())



        //Assigning listeners to buttons
        this.button!!.setOnClickListener(View.OnClickListener {
            presenter!!.onButtonClickForecast()
        })

        this.back!!.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, MainActivity::class.java )
            startActivity(intent)
        })
    }

    override fun getdata(): List<Any> {

        progressbar!!.visibility = View.VISIBLE
        dips!!.visibility = View.INVISIBLE

        val location = location!!.text.toString()
        val days = 1
        return listOf(location, days)
    }

    override fun display(display: List<Any>?) {

        val to_display:String
        val icon_url:Any

        when (display){
            null -> {
                to_display = "No Data\nNo Data, No Data"
                icon_url = R.drawable.image_temp
            }
            else -> {
                to_display = "${display[2]}\n${display[0]},${display[1]}"
                icon_url = "https:${display[3]}"
            }
        }

        dips!!.text = to_display

        forecast_icon!!.load(
            /*if (icon_url == "") R.drawable.image_temp else icon_url*/
            icon_url
        ){
            crossfade(true)
            placeholder(R.drawable.image_temp)
            transformations(CircleCropTransformation())
        }
        progressbar!!.visibility = View.INVISIBLE
        dips!!.visibility = View.VISIBLE
    }

}