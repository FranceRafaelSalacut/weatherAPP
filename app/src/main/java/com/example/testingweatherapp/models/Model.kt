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

@DelicateCoroutinesApi
class Model :  Contract.Model{

    /*var forecastData: ForecastData.forecastData?= null*/

    override fun __init__data(/*URL:String*/location: String, days: Int, listener: Contract.Model.onfinishedListener){
        //Log.d("this", URL)
        Log.d("this", "We are inside the INIT DATA you sonavabetch")
        //debug().apicall(URL)
        /*return apiCall(URL)*/
        //apiCall(URL)
        //return apiCall2(location, days)

        var forecastData: ForecastData.forecastData?= null
        val forecastdataApi = RetrofitHelper.getInstance().create(ApiServices::class.java)
        Log.d("This", "Something was done here")
        // launching a new coroutine
        val result = forecastdataApi.getForecastData(
            apiKey = Constants.apiKey,
            location = location,
            days = days,
            aqi = Constants.aqi,
            alerts = Constants.alerts
        )

        Log.d("This", "before the data parse $forecastData.toString()")


        result.enqueue(object: Callback<ForecastData.forecastData>{
            override fun onResponse(call: Call<ForecastData.forecastData>, response: Response<ForecastData.forecastData>) {
                if(response.isSuccessful){
                    Log.d("this", response.body().toString())
                    Log.d("this", response.body()!!.location.name)
                    Log.d("this", response.body()!!.location.country)
                    forecastData = response.body()
                    Log.d("This", "During data Parse $forecastData.toString()")
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

        Log.d("this", "Went through that. not happy")

        Log.d("This", "After the data parse $forecastData.toString()")

    }

    /*fun apiCall2(location: String, days: Int): ForecastData.forecastData?{

        var forecastData: ForecastData.forecastData?= null
        val forecastdataApi = RetrofitHelper.getInstance().create(ApiServices::class.java)
        Log.d("This", "Something was done here")
        // launching a new coroutine
        val result = forecastdataApi.getForecastData(
            apiKey = Constants.apiKey,
            location = location,
            days = days,
            aqi = Constants.aqi,
            alerts = Constants.alerts
        )

        Log.d("This", "before the data parse $forecastData.toString()")


        result.enqueue(object: Callback<ForecastData.forecastData>{
            override fun onResponse(call: Call<ForecastData.forecastData>, response: Response<ForecastData.forecastData>) {
                if(response.isSuccessful){
                    Log.d("this", response.body().toString())
                    Log.d("this", response.body()!!.location.name)
                    Log.d("this", response.body()!!.location.country)
                    forecastData = response.body()
                    Log.d("This", "During data Parse $forecastData.toString()")
                }else{
                    Log.d("this", "AINT ASUCCSFSDFSEFA")
                }
            }
            override fun onFailure(call: Call<ForecastData.forecastData>, t: Throwable) {
                Log.d("this", "FAILURE!! ")
            }
        })

        Log.d("this", "Went through that. not happy")

        Log.d("This", "After the data parse $forecastData.toString()")

        return forecastData
    }*/


    /*fun apiCall(url: String){
        Log.d("This", "you are in the real world now")
        val client = OkHttpClient()

        val request = Request.Builder()
            .url(url)
            .build()

        client.newCall(request).enqueue(object : okhttp3.Callback {
            override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {
                //Log.d("This", response.body()?.string() ?: "NULL")
                val responseData = response.body()?.string()?:"NULL"

                val jsonTokener = JSONTokener(responseData)
                val data = jsonTokener.nextValue() as JSONObject

                Log.d("this", "We are inside the API CALL")
                Log.d("This", data.toString())

                forecastData = Json.decodeFromString<ForecastData.forecastData>(data.toString())
                Log.d("this", forecastData.toString())
                Log.d("this", forecastData!!.location.name)


                val location = Json.decodeFromString<ForecastData.location>(data.getJSONObject("location").toString())
                Log.d("this", location.toString())
                val current = Json.decodeFromString<ForecastData.current>(data.getJSONObject("current").toString())
                Log.d("this", current.toString())
                val forecast = Json.decodeFromString<ForecastData.forecast>(data.getJSONObject("forecast").toString())
                //Log.d("this", data.getJSONObject("forecast").getJSONArray("forecastday").toString())
                Log.d("this", forecast.toString())

            }

            override fun onFailure(call: okhttp3.Call, e: IOException) {
                Log.d("this", "you failed bitch")
                Log.d("This", e.toString())
            }

        })
    }*/
}