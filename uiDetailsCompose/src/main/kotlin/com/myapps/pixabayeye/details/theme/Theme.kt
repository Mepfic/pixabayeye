package com.myapps.pixabayeye.details.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = primaryDark,
    primaryVariant = primaryVariantDark,
    onPrimary = onPrimaryDark,
    secondary = secondaryDark,
    secondaryVariant = secondaryVariantDark,
    onSecondary = onSecondaryDark,
    error = errorDark,
    onError = onErrorDark,
    background = backgroundDark,
    onBackground = onBackgroundDark,
    surface = surfaceDark,
    onSurface = onSurfaceDark,
)

private val LightColorPalette = lightColors(
    primary = primaryLight,
    primaryVariant = primaryVariantLight,
    onPrimary = onPrimaryLight,
    secondary = secondaryLight,
    secondaryVariant = secondaryVariantLight,
    onSecondary = onSecondaryLight,
    error = errorLight,
    onError = onErrorLight,
    background = backgroundLight,
    onBackground = onBackgroundLight,
    surface = surfaceLight,
    onSurface = onSurfaceLight,
)

@Composable
fun PixabayTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable() () -> Unit) {
    val colors = if(darkTheme){
        DarkColorPalette
    } else{
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = RobotoTypography,
        shapes = AppShapes,
        content = content
    )
}