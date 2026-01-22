package com.myapps.pixabayeye.common.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val primaryLight = Color(0xFF6200EE)
val onPrimaryLight = Color(0xFFFFFFFF)
val secondaryLight = Color(0xFF03DAC6)
val onSecondaryLight = Color(0xFF000000)
val surfaceLight = Color(0xFFF3F3F3)
val onSurfaceLight = Color(0xFF000000)
val backgroundLight = Color(0xFFFFFFFF)
val onBackgroundLight = Color(0xFF000000)
val errorLight = Color(0xFFB00020)
val onErrorLight = Color(0xFFFFFFFF)

val primaryDark = Color(0xFFBB86FC)
val onPrimaryDark = Color(0xFF000000)
val secondaryDark = Color(0xFF0307DA)
val onSecondaryDark = Color(0xFF000000)
val surfaceDark = Color(0xFF121212)
val onSurfaceDark = Color(0xFFFFFFFF)
val backgroundDark = Color(0xFF121212)
val onBackgroundDark = Color(0xFFFFFFFF)
val errorDark = Color(0xFFCF6679)
val onErrorDark = Color(0xFF000000)

val ColorScheme.warning: Color
    @Composable get() = Color(0xFFFF9800)

