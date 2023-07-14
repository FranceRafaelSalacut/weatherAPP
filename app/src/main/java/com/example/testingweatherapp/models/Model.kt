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

    override fun __init__data(location: String, days: Int, listener: Contract.Model.onfinishedListener){
        Log.d("this", "We are inside the INIT DATA you sonavabetch")

        var forecastData: ForecastData.forecastData?= null
        val forecastdataApi = RetrofitHelper.getInstance().create(ApiServices::class.java)

        val result = forecastdataApi.getForecastData(
            apiKey = Constants.apiKey,
            location = location,
            days = days,
            aqi = Constants.aqi,
            alerts = Constants.alerts
        )

        result.enqueue(object: Callback<ForecastData.forecastData>{
            override fun onResponse(call: Call<ForecastData.forecastData>, response: Response<ForecastData.forecastData>) {
                if(response.isSuccessful){
                    forecastData = response.body()
                    listener.onFinished(forecastData)
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