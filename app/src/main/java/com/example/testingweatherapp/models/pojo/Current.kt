package com.example.testingweatherapp.models.pojo

import kotlinx.serialization.Serializable

class Current{

    @Serializable
    data class forecastData(
        val location: ForecastData.location,
        val current: ForecastData.current

        )
}

