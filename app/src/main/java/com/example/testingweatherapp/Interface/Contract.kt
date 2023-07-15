package com.example.testingweatherapp.Interface

import android.media.Image
import android.widget.ImageView
import com.example.testingweatherapp.models.ForecastData
import com.example.testingweatherapp.models.SearchData

interface Contract {

    interface View{
        fun getdata(): List<Any>{TODO("NOTHING")}

        fun display(display: List<Any>?){TODO("NOTHING")}

        /*fun display(location_name:String, location_region: String, forecast: String, icon_url: Any)*/
    }

    interface Model{

        interface onfinishedListener {

            /*fun onFinished(weather_Data: ForecastData.forecastData?)*/
            fun onFinished(data: Any?){TODO("NOTHING")}

        }
        fun __init__data(type: String, data: List<Any>, listener: onfinishedListener){TODO("NOTHING")}
        /*fun __init__data(*//*URL:String*//*location: String, day: Int, listener: onfinishedListener)*/
        /*fun __init__data(URL:String): Boolean*/

        fun getIcon(
            listofLocation: List<SearchData.location>,
            position: Int,
            location_data: MutableList<List<String>>,
            listener: Contract.Model.onfinishedListener
        ) {TODO("NOTHING")}

        fun getIconNEW(
            Location: String,
            instance: ImageView
        ){TODO("NOTHING")}

    }

    interface Presenter{
        fun onButtonClickForecast(){TODO("NOTHING")}
        fun onButtonClikcSearch(){TODO("NOTHING")}
        fun onButtonClick(from:String){TODO("NOTHING")}

    }

}