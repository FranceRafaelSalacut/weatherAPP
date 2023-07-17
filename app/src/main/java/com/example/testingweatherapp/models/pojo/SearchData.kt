package com.example.testingweatherapp.models.pojo

import kotlinx.serialization.Serializable

@Serializable
class SearchData {
    @Serializable
    class location(
        val id: Int,
        val name: String,
        val region: String,
        val country: String,
        val lat: Double,
        val lon: Double,
        val url: String
    )

    @Serializable
    data class location_data(
        val location: List<location>
    )
}