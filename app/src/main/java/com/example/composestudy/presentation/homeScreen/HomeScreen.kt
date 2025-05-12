package com.example.composestudy.presentation.homeScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composestudy.navigation.Screens
import com.example.composestudy.presentation.theme.ComposeStudyTheme

@Composable
internal fun HomeScreen(modifier: Modifier, onButtonClickListener: (Screens) -> Unit) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        contentPadding = PaddingValues(5.dp),
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        item {
            Button(onClick = { onButtonClickListener(Screens.TrafficLights) }) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "${Screens.TrafficLights}")
                    HorizontalDivider()
                    Text(
                        text = Screens.TrafficLights.description,
                        fontStyle = FontStyle.Italic,
                        fontSize = 8.sp
                    )
                }
            }
        }
        item {
            Button(onClick = { onButtonClickListener(Screens.ManyStores) }) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "${Screens.ManyStores}")
                    HorizontalDivider()
                    Text(
                        text = Screens.ManyStores.description,
                        fontStyle = FontStyle.Italic,
                        fontSize = 8.sp
                    )
                }
            }
        }
        item {
            Button(onClick = { onButtonClickListener(Screens.Decompose) }) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "${Screens.Decompose}")
                    HorizontalDivider()
                    Text(
                        text = Screens.Decompose.description,
                        fontStyle = FontStyle.Italic,
                        fontSize = 8.sp
                    )
                }
            }
        }
    }
}

@PreviewLightDark
@Composable
fun HomeScreen_Preview() {
    ComposeStudyTheme {
        HomeScreen(modifier = Modifier) {

        }
    }
}