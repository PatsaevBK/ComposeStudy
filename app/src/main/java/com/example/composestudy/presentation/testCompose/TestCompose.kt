package com.example.composestudy.presentation.testCompose

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun Test() {
    Box {
        Text(buildAnnotatedString {
            withStyle(style = SpanStyle(color = Color.Cyan, )) {
                append("11")
            }
            withStyle(style = SpanStyle(color = Color.Red)) {
                append("11")
            }
        })

    }
}

@Preview
@Composable
fun Preview_Test() {
    Test()
}