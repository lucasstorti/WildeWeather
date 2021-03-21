package com.example.androiddevchallenge.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.ui.theme.Snow
import com.example.androiddevchallenge.ui.theme.SnowFlakes

const val SNOWFLAKE_SIZE = 10
const val NUMBER_OF_SNOWFLAKES = 20
const val SNOW_ANIMATION_TARGET_VALUE = 5f

data class SnowflakeData (
    val sizeModifier: Float,
    val offsetX: Dp,
    val animationStartValue: Float,
    val animationEndValue: Float,
)

@Composable
fun SnowAnimation(
    modifier: Modifier = Modifier,
) {
    val infiniteTransition = rememberInfiniteTransition()

    val animatedValue by infiniteTransition.animateFloat(
        initialValue = 0F,
        targetValue = SNOW_ANIMATION_TARGET_VALUE,
        animationSpec = infiniteRepeatable(
            tween(8000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )

    val snowFlakeList = mutableListOf<SnowflakeData>()
    for(i in 0 until NUMBER_OF_SNOWFLAKES) {
        val randomAnimationStart = (0..(SNOW_ANIMATION_TARGET_VALUE - 2).toInt()).random()
        val randomAnimationEnd = (randomAnimationStart + 1..SNOW_ANIMATION_TARGET_VALUE.toInt()).random()

        val randomSizeModifier = (8..12).random()/10f

        val offsetX = SNOWFLAKE_SIZE * i
        val snowFlake = SnowflakeData(
            sizeModifier = randomSizeModifier,
            offsetX = offsetX.dp,
            animationStartValue = randomAnimationStart.toFloat(),
            animationEndValue = randomAnimationEnd.toFloat()
        )
        snowFlakeList.add(snowFlake)
    }

    Canvas(modifier = modifier
        .fillMaxWidth()
        .fillMaxHeight()) {
        translate(top = -SNOWFLAKE_SIZE.toFloat()) {
            val leftoverSpace = size.width - (NUMBER_OF_SNOWFLAKES * SNOWFLAKE_SIZE)
            for (i in 0 until NUMBER_OF_SNOWFLAKES) {
                val snowflake = snowFlakeList[i]
                val paddingHorizontal = (leftoverSpace / (NUMBER_OF_SNOWFLAKES - 1)) * i
                val topMargin =
                    if (animatedValue >= snowflake.animationStartValue && animatedValue <= snowflake.animationEndValue )
                        ((animatedValue - snowflake.animationStartValue) / (snowflake.animationEndValue - snowflake.animationStartValue)) * size.height
                    else
                        0f
                translate(top = topMargin) {
                    drawCircle(
                        brush = Brush.linearGradient(listOf(SnowFlakes, Snow)),
                        radius = SNOWFLAKE_SIZE * snowflake.sizeModifier,
                        Offset(snowflake.offsetX.toPx() + paddingHorizontal, 0F),
                    )
                }
            }
        }
    }
}
