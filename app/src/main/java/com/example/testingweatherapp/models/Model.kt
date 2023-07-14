package com.example.testingweatherapp.models

import kotlinx.serialization.json.Json
import android.util.Log
import org.json.JSONObject
import org.json.JSONTokener
import java.io.IOException
import com.example.testingweatherapp.Interface.Contract
import com.example.testingweatherapp.constants.Constants
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.serialization.json.decodeToSequence
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONArray
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.reflect.KClass

class Model :  Contract.Model{

    override fun __init__data(type: String, data: List<Any>, listener: Contract.Model.onfinishedListener){
        Log.d("this", "We are inside the INIT DATA you sonavabetch")

        val ApiServices = RetrofitHelper.getInstance().create(ApiServices::class.java)

        when (type){
            "forecast" -> {
                Log.d("this", type)
                val result = ApiServices.getForecastData(
                    apiKey = Constants.apiKey,
                    location = data[0].toString(),
                    days = Integer.parseInt(data[1].toString()),
                    aqi = Constants.aqi,
                    alerts = Constants.alerts
                )

                Log.d("this", "inside frerere ${data[0].toString()}")


                result.enqueue(object: Callback<ForecastData.forecastData>{
                    override fun onResponse(call: Call<ForecastData.forecastData>, response: Response<ForecastData.forecastData>) {
                        if(response.isSuccessful){
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
            "search" -> {
                Log.d("this", type)

                val result = ApiServices.search_location(
                    apiKey = Constants.apiKey,
                    location = data[0].toString(),
                )

                Log.d("this", result.request().url.toString())

                Log.d("this", "inside frerere ${data[0].toString()}")

                result.enqueue(object: Callback<List<SearchData.location>>{
                    override fun onResponse(call: Call<List<SearchData.location>>, response: Response<List<SearchData.location>>) {
                        if(response.isSuccessful){
                            Log.d("this", response.body().toString())
                            var temp: List<SearchData.location>? = response.body()
                            listener.onFinished(temp)
                        }else{
                            Log.d("this", "AINT ASUCCSFSDFSEFA")
                            listener.onFinished(null)
                        }
                    }
                    override fun onFailure(call: Call<List<SearchData.location>>, t: Throwable) {
                        Log.d("this", "FAILURE!! $t ")
                        listener.onFinished(null)
                    }
                })
            }
            else -> {
                listener.onFinished(null)
            }
        }

    }

}