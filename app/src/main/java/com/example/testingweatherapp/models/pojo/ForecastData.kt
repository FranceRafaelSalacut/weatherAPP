package com.example.testingweatherapp.models.pojo

import kotlinx.serialization.Serializable

@Serializable
class ForecastData{
    data class test(
        val test: String
    )

    @Serializable
    data class location(
        val name: String,
        val region: String,
        val country: String,
        val lat: Double,
        val lon: Double,
        val tz_id: String,
        val localtime_epoch: Int,
        val localtime: String
    )

    @Serializable
    data class condition(
        val text: String,
        val icon: String,
        val code: Int
    )

    @Serializable
    data class current(
        val last_updated_epoch: Int,
        val last_updated: String,
        val temp_c: Double,
        val temp_f: Double,
        val is_day: Int,
        val condition: condition,
        val wind_mph: Double,
        val wind_kph: Double,
        val wind_degree: Int,
        val wind_dir: String,
        val pressure_mb: Double,
        val pressure_in: Double,
        val precip_mm: Double,
        val precip_in: Double,
        val humidity: Int,
        val cloud: Int,
        val feelslike_c: Double,
        val feelslike_f: Double,
        val vis_km: Double,
        val vis_miles: Double,
        val uv: Double,
        val gust_mph: Double,
        val gust_kph: Double
    )

    @Serializable
    data class day(
        val maxtemp_c: Double,
        val maxtemp_f: Double,
        val mintemp_c: Double,
        val mintemp_f: Double,
        val avgtemp_c: Double,
        val avgtemp_f: Double,
        val maxwind_mph: Double,
        val maxwind_kph: Double,
        val totalprecip_mm: Double,
        val totalprecip_in: Double,
        val totalsnow_cm: Double,
        val avgvis_km: Double,
        val avgvis_miles: Double,
        val avghumidity: Double,
        val daily_will_it_rain: Int,
        val daily_chance_of_rain: Int,
        val daily_will_it_snow: Int,
        val daily_chance_of_snow: Int,
        val condition: condition,
        val uv: Double
    )


    @Serializable
    data class astro(
        val sunrise: String,
        val sunset: String,
        val moonrise: String,
        val moonset: String,
        val moon_phase: String,
        val moon_illumination: Int,
        val is_moon_up: Int,
        val is_sun_up: Int
    )

    @Serializable
    data class hour(
        val time_epoch: Int,
        val time: String,
        val temp_c: Double,
        val temp_f: Double,
        val is_day: Int,
        val condition: condition,
        val wind_mph: Double,
        val wind_kph: Double,
        val wind_degree: Int,
        val wind_dir: String,
        val pressure_mb: Double,
        val pressure_in: Double,
        val precip_mm: Double,
        val precip_in: Double,
        val humidity: Int,
        val cloud: Int,
        val feelslike_c: Double,
        val feelslike_f: Double,
        val windchill_c: Double,
        val windchill_f: Double,
        val heatindex_c: Double,
        val heatindex_f: Double,
        val dewpoint_c: Double,
        val dewpoint_f: Double,
        val will_it_rain: Int,
        val chance_of_rain: Int,
        val will_it_snow: Int,
        val chance_of_snow: Int,
        val vis_km: Double,
        val vis_miles: Double,
        val gust_mph: Double,
        val gust_kph: Double,
        val uv: Double
    )

    @Serializable
    data class forecastday(
        val date: String,
        val date_epoch: Int,
        val day: day,
        val astro: astro,
        val hour: List<hour>
    )

    @Serializable
    data class forecast(
        val forecastday: List<forecastday>
    )

    @Serializable
    data class forecastData(
        val location: location,
        val current: current,
        val forecast: forecast
    )
}