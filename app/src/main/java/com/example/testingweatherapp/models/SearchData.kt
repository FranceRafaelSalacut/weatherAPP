package com.example.testingweatherapp.models

class SearchData {
    class location_data(
        val id: String,
        val name: String,
        val region: String,
        val country: String,
        val lat: Double,
        val lon: Double,
        val url: String
    )
    data class location(
        val location: List<location_data>
    )
}