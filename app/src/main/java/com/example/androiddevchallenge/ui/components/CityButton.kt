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

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.library.models.City
import com.example.androiddevchallenge.ui.theme.Typography
import com.example.androiddevchallenge.ui.theme.Yellow

val CITY_BUTTON_SHAPE = RoundedCornerShape(10.dp)

@Composable
fun CityButton(city: City, selectedCity: City, onClick: (City) -> Unit) {
    ButtonBackground(
        onClick = { onClick(city) },
        content = {
            Text(
                text = city.name,
                color = Color.White,
                style = Typography.h2,
                modifier = Modifier.weight(1f)
            )
            Row(Modifier.alpha(0.8f)) {
                Text(text = "${city.temperature}°", color = Color.White, style = Typography.body1, modifier = Modifier.weight(1f))
                Icon(
                    Icons.Rounded.Star,
                    contentDescription = null,
                    tint = Color.White,
                )
            }
        },
        borderColor = if (city.name == selectedCity.name) Yellow else Color.Black,
    )
}

@Composable
fun ButtonBackground(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit,
    onClick: () -> Unit,
    borderColor: Color = Color.Black,
) {
    Column(
        modifier = modifier
            .width(200.dp)
            .height(160.dp)
            .padding(10.dp)
            .shadow(elevation = 3.dp, shape = CITY_BUTTON_SHAPE)
            .clip(CITY_BUTTON_SHAPE)
            .background(Color.Black)
            .clickable { onClick() }
            .border(
                width = 2.dp,
                color = borderColor,
                shape = CITY_BUTTON_SHAPE,
            )
            .padding(10.dp),
        content = content
    )
}
