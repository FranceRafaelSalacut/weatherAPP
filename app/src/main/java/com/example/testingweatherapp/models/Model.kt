package com.example.testingweatherapp.models

import android.util.Log
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
import java.text.FieldPosition
import java.util.concurrent.CountDownLatch
import kotlin.reflect.KClass

class Model :  Contract.Model{

    private val ApiServices = RetrofitHelper.getInstance().create(ApiServices::class.java)

    override fun __init__data(type: String, data: List<Any>, listener: Contract.Model.onfinishedListener){
        Log.d("this", "We are inside the INIT DATA you sonavabetch")


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
                            val search_data: List<SearchData.location> = response.body()!!
                            val Location_data: MutableList<List<String>> = mutableListOf()
                            getIcon(search_data , 0, Location_data, listener)

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

    override fun getIcon(
        ListOfLocation: List<SearchData.location>,
        position: Int,
        location_data: MutableList<List<String>>,
        listener: Contract.Model.onfinishedListener) {

        Log.d("this", "position ==== $position")
        if(position == ListOfLocation.size){
            listener.onFinished(location_data)
            return
        }

        Log.d("this", "passed the check")

        var icon_url: String = ""
        val name: String = ListOfLocation[position].name
        val region: String = ListOfLocation[position].region
        val country: String = ListOfLocation[position].country
        val location: String = "$name, $region, $country"

        val result = ApiServices.getCurrent(
            apiKey = Constants.apiKey,
            location = location,
            aqi = Constants.aqi
        )


        Log.d("this", result.request().url.toString())


        result.enqueue(object: Callback<Current.forecastData>{
            override fun onResponse(call: Call<Current.forecastData>, response: Response<Current.forecastData>) {
                if(response.isSuccessful){
                    Log.d("this", response.body().toString())
                    val current: Current.forecastData? = response.body()
                    icon_url = current?.current?.condition?.icon?:""
                    location_data.add(listOf(location, icon_url))
                    getIcon(ListOfLocation, position+1, location_data, listener)

                }else{
                    Log.d("this", "AINT ASUCCSFSDFSEFA")
                    location_data.add(listOf(location, icon_url))
                    listener.onFinished(location_data)

                }
            }
            override fun onFailure(call: Call<Current.forecastData>, t: Throwable) {
                Log.d("this", "FAILURE!! $t ")
                location_data.add(listOf(location, icon_url))
                listener.onFinished(location_data)
            }
        })
    }


}