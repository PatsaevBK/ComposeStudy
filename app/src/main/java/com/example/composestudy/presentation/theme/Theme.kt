package com.example.composestudy.presentation.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val LightColorScheme = darkColorScheme(
    primary = Color(0xFF00796B), // Глубокий бирюзовый
    onPrimary = Color(0xFFFFFFFF),
    primaryContainer = Color(0xFFB2DFDB),
    onPrimaryContainer = Color(0xFF004D40),

    secondary = Color(0xFF558B2F), // Тёмно-зелёный
    onSecondary = Color(0xFFFFFFFF),
    secondaryContainer = Color(0xFFDCE775),
    onSecondaryContainer = Color(0xFF33691E),

    tertiary = Color(0xFFF57C00), // Оранжевый
    onTertiary = Color(0xFFFFFFFF),
    tertiaryContainer = Color(0xFFFFECB3),
    onTertiaryContainer = Color(0xFFE65100),

    background = Color(0xFFF5F5F5), // Светлый серый
    onBackground = Color(0xFF212121),
    surface = Color(0xFFFFFFFF),
    onSurface = Color(0xFF424242),

    error = Color(0xFFD32F2F),
    onError = Color(0xFFFFFFFF),
    errorContainer = Color(0xFFFFCDD2),
    onErrorContainer = Color(0xFFB71C1C)
)

private val DarkColorScheme = lightColorScheme(
    primary = Color(0xFF009688), // Светлый бирюзовый
    onPrimary = Color(0xFF000000),
    primaryContainer = Color(0xFF004D40),
    onPrimaryContainer = Color(0xFFB2DFDB),

    secondary = Color(0xFF8BC34A), // Яркий зелёный
    onSecondary = Color(0xFF000000),
    secondaryContainer = Color(0xFF33691E),
    onSecondaryContainer = Color(0xFFDCE775),

    tertiary = Color(0xFFFF9800), // Яркий оранжевый
    onTertiary = Color(0xFF000000),
    tertiaryContainer = Color(0xFFE65100),
    onTertiaryContainer = Color(0xFFFFECB3),

    background = Color(0xFF303030), // Тёмно-серый
    onBackground = Color(0xFFF5F5F5),
    surface = Color(0xFF424242),
    onSurface = Color(0xFFFFFFFF),

    error = Color(0xFFCF6679),
    onError = Color(0xFF000000),
    errorContainer = Color(0xFFB00020),
    onErrorContainer = Color(0xFFFFCDD2)
)

@Composable
fun ComposeStudyTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}