package com.example.testingweatherapp.presenter

import android.util.Log
import com.example.testingweatherapp.Interface.Contract
import com.example.testingweatherapp.models.pojo.ForecastData
import com.example.testingweatherapp.models.pojo.SearchData


class presenter (private var mainView: Contract.View, private var model: Contract.Model) : Contract.Presenter, Contract.Model.onfinishedListener {

    override fun onButtonClick(from: String) {
        Log.d("this", "The new era has begun")
        model.getData(
            type = from,
            data = mainView.getdata(),
            listener = this,
            instance = null
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
                val search_data = data as List<SearchData.location>
                display = mutableListOf()

                for(location in search_data){
                    display.add("${location.name}, ${location.region}, ${location.country} ")
                }

            }
            else -> {
                Log.d("this", "null me bitch")
                display = null
            }
        }

        mainView.display(display)
    }


}