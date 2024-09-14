package com.example.composestudy.presentation.trafficLight

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.composestudy.presentation.trafficLight.store.TrafficLightView.Model.Colors
import com.example.composestudy.presentation.trafficLight.store.TrafficLightView.Model.Colors.GREEN
import com.example.composestudy.presentation.trafficLight.store.TrafficLightView.Model.Colors.RED
import com.example.composestudy.presentation.trafficLight.store.TrafficLightView.Model.Colors.YELLOW

@Composable
internal fun TrafficLight(
    modifier: Modifier,
    selectedColors: Colors,
) {
    Canvas(modifier = modifier.fillMaxSize()) {
        val width = size.width / 2
        val height = size.height / 2


        drawRoundRect(
            topLeft = Offset(width / 2, height / 2),
            color = bodyColor,
            size = Size(width, height),
            cornerRadius = CornerRadius(size.width / 10f, size.width / 10f),
        )

        drawCircle(
            color = redColor,
            alpha = if (selectedColors == RED) 1f else 0.5f,
            radius = width / 4,
            center = Offset(center.x, center.y - height / 3),
        )

        drawCircle(
            color = yellowColor,
            alpha = if (selectedColors == YELLOW) 1f else 0.5f,
            radius = width / 4,
            center = Offset(center.x, center.y),
        )

        drawCircle(
            color = greenColor,
            alpha = if (selectedColors == GREEN) 1f else 0.5f,
            radius = width / 4,
            center = Offset(center.x, center.y + height / 3),
        )
    }
}

private val bodyColor = Color(0xFF4C5760)
private val redColor = Color(0xFFFF0000)
private val greenColor = Color(0xFF008000)
private val yellowColor = Color(0xFFFFFF00)

@Preview
@Composable
internal fun TrafficLight_Preview() {
    TrafficLight(modifier = Modifier, selectedColors = RED)
}