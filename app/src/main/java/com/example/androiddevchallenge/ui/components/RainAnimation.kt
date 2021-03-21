package com.example.androiddevchallenge.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.ui.theme.OxfordBlue

val DROP_WIDTH = 5.dp
val DROP_HEIGHT = 30.dp
const val NUMBER_OF_DROPS = 20
const val ANIMATION_TARGET_VALUE = 5f

data class DropData (
    val width: Dp,
    val height: Dp,
    val offsetX: Dp,
    val animationStartValue: Float,
    val animationEndValue: Float,
)

@Composable
fun RainAnimation(
    modifier: Modifier = Modifier,
) {
    val infiniteTransition = rememberInfiniteTransition()

    val animatedValue by infiniteTransition.animateFloat(
        initialValue = 0F,
        targetValue = ANIMATION_TARGET_VALUE,
        animationSpec = infiniteRepeatable(
            tween(2000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )

    val dropList = mutableListOf<DropData>()
    for(i in 0 until NUMBER_OF_DROPS) {
        val randomAnimationStart = (0..(ANIMATION_TARGET_VALUE - 2).toInt()).random()
        val randomAnimationEnd = (randomAnimationStart + 1..ANIMATION_TARGET_VALUE.toInt()).random()

        val offsetX = DROP_WIDTH * i
        val drop = DropData(
            DROP_WIDTH,
            DROP_HEIGHT,
            offsetX,
            randomAnimationStart.toFloat(),
            randomAnimationEnd.toFloat()
        )
        dropList.add(drop)
    }

    Canvas(modifier = modifier
        .fillMaxWidth()
        .fillMaxHeight()) {
        translate(top = -DROP_HEIGHT.toPx()) {
            val leftoverSpace = size.width - (NUMBER_OF_DROPS * DROP_WIDTH.toPx())
            for (i in 0 until NUMBER_OF_DROPS) {
                val drop = dropList[i]
                val paddingHorizontal = (leftoverSpace / (NUMBER_OF_DROPS - 1)) * i
                val topMargin =
                    if (animatedValue >= drop.animationStartValue && animatedValue <= drop.animationEndValue )
                        ((animatedValue - drop.animationStartValue) / (drop.animationEndValue - drop.animationStartValue)) * size.height
                    else
                        0f
                translate(top = topMargin) {
                    drawRect(
                        brush = Brush.linearGradient(
                            listOf(OxfordBlue, Color.White),
                            start = Offset(0.0f, 10.0f),
                            end = Offset(0.0f, 100.0f)
                        ),
                        Offset(drop.offsetX.toPx() + paddingHorizontal, 0F),
                        size = Size(drop.width.toPx(), drop.height.toPx()),
                        alpha = 0.6f
                    )
                }
            }
        }
    }
}