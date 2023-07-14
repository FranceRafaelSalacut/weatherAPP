package com.example.testingweatherapp.models

import kotlinx.serialization.json.Json
import android.util.Log
import org.json.JSONObject
import org.json.JSONTokener
import java.io.IOException
import com.example.testingweatherapp.Interface.Contract
import com.example.testingweatherapp.constants.Constants
import kotlinx.coroutines.DelicateCoroutinesApi
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Model :  Contract.Model{

    override fun __init__data(type: String, data: List<Any>, listener: Contract.Model.onfinishedListener){
        Log.d("this", "We are inside the INIT DATA you sonavabetch")

        when (type){
            "forecast" -> Log.d("this", type)
            "search" -> Log.d("this", type)
            else -> TODO("Nothing to do")
        }


        var forecastData: ForecastData.forecastData?= null
        val ApiServices = RetrofitHelper.getInstance().create(ApiServices::class.java)

        val result = ApiServices.getForecastData(
            apiKey = Constants.apiKey,
            location = data[0].toString(),
            days = Integer.parseInt(data[1].toString()),
            aqi = Constants.aqi,
            alerts = Constants.alerts
        )

        result.enqueue(object: Callback<ForecastData.forecastData>{
            override fun onResponse(call: Call<ForecastData.forecastData>, response: Response<ForecastData.forecastData>) {
                if(response.isSuccessful){
                    /*forecastData = response.body()*/
                    listener.onFinished(response.body())
                }else{
                    Log.d("this", "AINT ASUCCSFSDFSEFA")
                    listener.onFinished(null)
                }
            }
            override fun onFailure(call: Call<ForecastData.forecastData>, t: Throwable) {
                Log.d("this", "FAILURE!! ")
                listener.onFinished(null)
            }
        })


    }

}