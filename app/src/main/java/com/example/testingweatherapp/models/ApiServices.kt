package com.example.testingweatherapp.models

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {
    @GET("forecast.json")
    fun getForecastData(
        @Query("key") apiKey: String,
        @Query("q") location: String,
        @Query("days") days: Int,
        @Query("aqi") aqi: String,
        @Query("alerts") alerts: String
    ): Call<ForecastData.forecastData>


    @GET("search.json")
    fun search_location(
        @Query("key") apiKey: String,
        @Query("q") location: String
    ): Call<SearchData.location>

    @GET("")
    fun dummy(): Call<Any>
}