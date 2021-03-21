package com.example.androiddevchallenge.library.models

import androidx.compose.runtime.Immutable

enum class WeatherType {
    CLEAR, RAIN, SNOW
}

@Immutable
data class City(
    val name: String,
    val temperature: Int,
    val weatherType: WeatherType,
)

val cities = listOf(
    City(
        name = "Nicodranas",
        temperature = 68,
        weatherType = WeatherType.CLEAR
    ),
    City(
        name = "Zadash",
        temperature = 50,
        weatherType = WeatherType.RAIN
    ),
    City(
        name = "Draconia",
        temperature = 14,
        weatherType = WeatherType.SNOW
    ),
)
