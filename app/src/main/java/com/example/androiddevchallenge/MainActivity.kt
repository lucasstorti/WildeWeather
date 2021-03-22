/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import com.example.androiddevchallenge.library.models.WeatherType
import com.example.androiddevchallenge.library.models.cities
import com.example.androiddevchallenge.ui.components.CityButton
import com.example.androiddevchallenge.ui.components.ClearAnimation
import com.example.androiddevchallenge.ui.components.RainAnimation
import com.example.androiddevchallenge.ui.components.SelectedCity
import com.example.androiddevchallenge.ui.components.SnowAnimation
import com.example.androiddevchallenge.ui.theme.Orange
import com.example.androiddevchallenge.ui.theme.OxfordBlue
import com.example.androiddevchallenge.ui.theme.Snow
import com.example.androiddevchallenge.ui.theme.SnowFlakes
import com.example.androiddevchallenge.ui.theme.WildeWeatherTheme
import com.example.androiddevchallenge.ui.theme.Yellow

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WildeWeatherTheme {
                WildeWeather()
            }
        }
    }
}

@Composable
fun WildeWeather() {
    var selectedCity by remember { mutableStateOf(cities[0]) }
    val scrollState = rememberScrollState()

    val transition = updateTransition(selectedCity)

    val backgroundTopColor: Color by transition.animateColor {
        when (it.weatherType) {
            WeatherType.SNOW -> Snow
            WeatherType.RAIN -> OxfordBlue
            WeatherType.CLEAR -> Yellow
        }
    }

    val backgroundBottomColor: Color by transition.animateColor {
        when (it.weatherType) {
            WeatherType.SNOW -> SnowFlakes
            WeatherType.RAIN -> OxfordBlue
            WeatherType.CLEAR -> Orange
        }
    }

    Box(
        modifier = Modifier
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        backgroundTopColor,
                        backgroundBottomColor
                    )
                )
            )
    ) {
        Crossfade(targetState = selectedCity) {
            when (it.weatherType) {
                WeatherType.SNOW -> SnowAnimation()
                WeatherType.RAIN -> RainAnimation()
                WeatherType.CLEAR -> ClearAnimation()
            }
        }
        Column(
            Modifier
                .fillMaxHeight()
                .fillMaxWidth()
        ) {
            SelectedCity(
                modifier = Modifier.weight(1f),
                city = selectedCity,
                transition = transition,
            )
            Row(Modifier.horizontalScroll(scrollState)) {
                cities.forEach { city ->
                    CityButton(
                        city = city,
                        selectedCity = selectedCity,
                        onClick = { selectedCity = it }
                    )
                }
            }
        }
    }
}
