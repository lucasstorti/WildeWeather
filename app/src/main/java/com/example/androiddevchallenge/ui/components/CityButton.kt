package com.example.wildeweather.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.semantics.CustomAccessibilityAction
import androidx.compose.ui.semantics.customActions
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.library.models.City
import com.example.androiddevchallenge.ui.theme.Typography
import com.example.androiddevchallenge.ui.theme.Yellow

val CITY_BUTTON_SHAPE = RoundedCornerShape(10.dp)

@Composable
fun CityButton(city: City, selectedCity: City, onClick: (City) -> Unit ) {
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