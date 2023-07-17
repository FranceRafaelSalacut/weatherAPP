package com.example.testingweatherapp.models

import android.util.Log
import android.widget.ImageView
import coil.load
import coil.transform.CircleCropTransformation
import com.example.testingweatherapp.Interface.Contract
import com.example.testingweatherapp.R
import com.example.testingweatherapp.constants.Constants
import com.example.testingweatherapp.models.pojo.Current
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Model :  Contract.Model{
    fun setIcon(data: Current.forecastData?, instance: ImageView?){
        val icon_url:String

        Log.d("this", "We here?")

        when(data){
            null -> icon_url=""
            else -> icon_url = "https:" + data.current.condition.icon
        }
        instance?.load(icon_url){
            crossfade(true)
            error(R.drawable.sunny_icon)
            placeholder(R.drawable.image_temp)
            transformations(CircleCropTransformation())
        }
    }
    fun <T> trying_to(result: Call<T>, listener: Contract.Model.onfinishedListener?, instance: ImageView?): Unit{

        Log.d("this", "NEW API CALL")
        Log.d("this", "We here? ${instance.toString()}")

        result.enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>
            ) {
                if (response.isSuccessful) {
                    listener?.onFinished(response.body())
                    if(instance!=null) setIcon(response.body() as Current.forecastData?, instance)

                } else {
                    Log.d("this", "AINT ASUCCSFSDFSEFA")
                    listener?.onFinished(null)
                    setIcon(null, instance)
                }
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                Log.d("this", "FAILURE!! ")
                listener?.onFinished(null)
                setIcon(null, instance)
            }
        })
    }

    override fun getData(type: String, data: List<Any>, listener: Contract.Model.onfinishedListener?, instance: ImageView?) {

        val ApiServices = RetrofitHelper.getInstance().create(ApiServices::class.java)

        when (type) {
            "forecast" -> {
                Log.d("this", "NEW $type")
                val result = ApiServices.getForecastData(
                    apiKey = Constants.apiKey,
                    location = data[0].toString(),
                    days = Integer.parseInt(data[1].toString()),
                    aqi = Constants.aqi,
                    alerts = Constants.alerts
                )
                trying_to(result, listener, instance)

            }

            "search" -> {
                Log.d("this", "NEW $type")
                val result = ApiServices.search_location(
                    apiKey = Constants.apiKey,
                    location = data[0].toString(),
                )
                trying_to(result, listener, instance)
            }

            "icon" -> {
                Log.d("this", "NEW $type")
                val result = ApiServices.getCurrent(
                    apiKey =  Constants.apiKey,
                    location = data[0].toString(),
                    aqi = Constants.aqi
                )
                trying_to(result, listener, instance)
            }

            else -> {
                listener?.onFinished(null)
            }
        }
    }


}