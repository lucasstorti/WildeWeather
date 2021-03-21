package com.example.androiddevchallenge.ui.components

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.Transition
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.foundation.layout.*
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

    Box(modifier = modifier
        .fillMaxWidth()
        .padding(10.dp)) {
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