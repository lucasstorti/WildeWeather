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
package com.example.androiddevchallenge.ui.components

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.Transition
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.library.models.City
import com.example.androiddevchallenge.library.models.WeatherType
import com.example.androiddevchallenge.ui.theme.ClearText
import com.example.androiddevchallenge.ui.theme.RainText
import com.example.androiddevchallenge.ui.theme.SnowText
import com.example.androiddevchallenge.ui.theme.Typography

@Composable
fun SelectedCity(modifier: Modifier = Modifier, city: City, transition: Transition<City>) {
    val temperatureAnimation: Int by animateIntAsState(
        targetValue = city.temperature,
    )

    val textColor by transition.animateColor {
        when (it.weatherType) {
            WeatherType.SNOW -> SnowText
            WeatherType.RAIN -> RainText
            WeatherType.CLEAR -> ClearText
        }
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        Column(Modifier.padding(horizontal = 10.dp)) {
            Text(
                text = city.name,
                style = Typography.h2,
                color = textColor,
            )
            Text(
                text = "Folsen, Misuthar 18th, 836 PD",
                color = textColor
            )
        }
        Row(modifier = Modifier.align(Alignment.Center)) {
            Text(
                text = "$temperatureAnimation",
                style = Typography.h1,
                color = textColor,
            )
            Text(
                text = "°",
                modifier = Modifier.padding(vertical = 34.dp),
                style = Typography.h2,
                color = textColor,
            )
        }
    }
}
