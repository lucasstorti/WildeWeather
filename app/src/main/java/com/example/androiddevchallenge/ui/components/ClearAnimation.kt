package com.example.androiddevchallenge.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.drawscope.translate
import com.example.androiddevchallenge.ui.theme.Champagne
import com.example.androiddevchallenge.ui.theme.Yellow

@Composable
fun ClearAnimation(
    modifier: Modifier = Modifier,
) {
    val infiniteTransition = rememberInfiniteTransition()

    val animatedValue by infiniteTransition.animateFloat(
        initialValue = 0.99F,
        targetValue = 1.1F,
        animationSpec = infiniteRepeatable(
            tween(2000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    Canvas(modifier = modifier
        .fillMaxWidth()
        .fillMaxHeight()) {
        translate(top = -size.height/2, left = size.width/2) {
            drawCircle(
                brush = Brush.radialGradient(listOf(Champagne, Yellow)),
                radius = animatedValue * 300
            )
        }
    }
}
