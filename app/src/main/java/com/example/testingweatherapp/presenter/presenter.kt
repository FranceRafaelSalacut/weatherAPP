package com.example.testingweatherapp.presenter

import android.util.Log
import com.example.testingweatherapp.Interface.Contract
import com.example.testingweatherapp.R
import com.example.testingweatherapp.models.ForecastData


class presenter (private var mainView: Contract.View, private var model: Contract.Model) : Contract.Presenter, Contract.Model.onfinishedListener {

    /*override  fun onButtonClick() {
        Log.d("this", "You clicked me? ")
        val URL = mainView.constructURL()
        if(model.__init__data(URL)){
            mainView.display("API CALL SUCESS")
        }else{
            mainView.display("API CALL BAD")
        }

    }*/

    override fun onButtonClickForecast() {
        Log.d("this", "Tempooooooooooooooorary")
        val data:List<Any> = mainView.getdata()
        model.__init__data(data[0].toString(), Integer.parseInt(data[1].toString()), this)

    }

    override fun onButtonClikcSearch() {
        val data:List<Any> = mainView.getdata()
    }

    override fun onFinished(weather_Data: ForecastData.forecastData?) {
        Log.d("this", "inside but on clck $weather_Data.toString()")
        Log.d("this", weather_Data?.current?.condition?.icon?:"No data")

        mainView.display(
            weather_Data?.location?.name?:"No data",
            weather_Data?.location?.country?:"No data",
            weather_Data?.current?.condition?.text?:"No data",
            weather_Data?.current?.condition?.icon?:R.drawable.image_temp
        )
    }

}