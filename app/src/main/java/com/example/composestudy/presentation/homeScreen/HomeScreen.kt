package com.example.composestudy.presentation.homeScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composestudy.navigation.Screens

@Composable
internal fun HomeScreen(modifier: Modifier, onButtonClickListener: (Screens) -> Unit) {
    LazyColumn(modifier = modifier.fillMaxSize().padding(4.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        item {
            Button(onClick = { onButtonClickListener(Screens.TrafficLights) }) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "${Screens.TrafficLights}")
                    HorizontalDivider()
                    Text(text = Screens.TrafficLights.description, fontStyle = FontStyle.Italic, fontSize = 8.sp)
                }
            }
        }
    }
}

@Preview
@Composable
fun HomeScreen_Preview() {
    HomeScreen(modifier = Modifier) {

    }
}