package com.example.testingweatherapp.presenter

import android.util.Log
import com.example.testingweatherapp.Interface.Contract
import com.example.testingweatherapp.R
import com.example.testingweatherapp.models.ForecastData
import com.example.testingweatherapp.models.SearchData
import kotlin.math.log


class presenter (private var mainView: Contract.View, private var model: Contract.Model) : Contract.Presenter, Contract.Model.onfinishedListener {

    override fun onButtonClickForecast() {
        Log.d("this", "Tempooooooooooooooorary")
        model.__init__data(
            "forecast",
            mainView.getdata(),
            this
        )

    }

    override fun onButtonClikcSearch() {
        model.__init__data(
            "search",
            mainView.getdata(),
            this
        )
    }

    override fun onButtonClick(from: String) {
        Log.d("one", "We are one now!!")
        model.__init__data(
            from,
            mainView.getdata(),
            this
        )
    }
    override fun onFinished(data: Any?) {
        val display: List<Any>?

        when (data){
            is ForecastData.forecastData -> {
                var weather_Data: ForecastData.forecastData? = data

                display = listOf(
                    weather_Data?.location?.name?:"No data",
                    weather_Data?.location?.country?:"No data",
                    weather_Data?.current?.condition?.text?:"No data",
                    weather_Data?.current?.condition?.icon?:""
                )
            }
            is List<*> ->{
                Log.d("this", "im here after everything")
                display = data as MutableList<List<String>>

            }
            else -> {
                Log.d("this", "null me bitch")
                display = null
            }
        }

        mainView.display(display)
    }


}