package com.example.testingweatherapp.A_testing

import retrofit2.Response
import retrofit2.http.GET

interface QuotesAPI {
    @GET("forecast.json?key=2ebf9aea9a1e493ca2b20050230707&q=Cebu&days=3&aqi=no&alerts=no")
    suspend fun getQuotes() : Response<QuoteList>
}