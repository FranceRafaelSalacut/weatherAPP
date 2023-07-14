package com.example.testingweatherapp.Interface

import com.example.testingweatherapp.models.ForecastData

interface Contract {

    interface View{
        fun getdata(): List<Any>
        /*fun display(location_name:String, location_region: String, forecast: String, icon_url: Any)*/
        fun display(display: List<Any>?)
    }

    interface Model{

        interface onfinishedListener {

            /*fun onFinished(weather_Data: ForecastData.forecastData?)*/
            fun onFinished(data: Any?)
        }

        fun __init__data(type: String, data: List<Any>, listener: onfinishedListener)
        /*fun __init__data(*//*URL:String*//*location: String, day: Int, listener: onfinishedListener)*/
        /*fun __init__data(URL:String): Boolean*/
    }

    interface Presenter{
        fun onButtonClickForecast()
        fun onButtonClikcSearch()
    }

}