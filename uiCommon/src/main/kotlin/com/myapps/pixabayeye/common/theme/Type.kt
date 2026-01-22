package com.myapps.pixabayeye.common.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.myapps.pixabayeye.common.R

private val Roboto = FontFamily(
    Font(R.font.roboto_light, FontWeight.W300),
    Font(R.font.roboto_regular, FontWeight.W400),
    Font(R.font.roboto_medium, FontWeight.W500),
    Font(R.font.roboto_bold, FontWeight.W600)
)

val RobotoTypography = Typography(
    displayLarge = TextStyle(
        fontFamily = Roboto,
        fontWeight = FontWeight.W500,
        fontSize = 32.sp
    ),

    displayMedium = TextStyle(
        fontFamily = Roboto,
        fontWeight = FontWeight.W500,
        fontSize = 26.sp
    ),

    displaySmall = TextStyle(
        fontFamily = Roboto,
        fontWeight = FontWeight.W500,
        fontSize = 22.sp
    ),

    headlineMedium = TextStyle(
        fontFamily = Roboto,
        fontWeight = FontWeight.W400,
        fontSize = 20.sp
    ),

    headlineSmall = TextStyle(
        fontFamily = Roboto,
        fontWeight = FontWeight.W400,
        fontSize = 18.sp
    ),

    titleLarge = TextStyle(
        fontFamily = Roboto,
        fontWeight = FontWeight.W400,
        fontSize = 16.sp
    ),

    titleMedium = TextStyle(
        fontFamily = Roboto,
        fontWeight = FontWeight.W500,
        fontSize = 15.sp
    ),

    titleSmall = TextStyle(
        fontFamily = Roboto,
        fontWeight = FontWeight.W400,
        fontSize = 14.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = Roboto,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = Roboto,
        fontSize = 14.sp
    ),
    labelLarge = TextStyle(
        fontFamily = Roboto,
        fontWeight = FontWeight.W400,
        fontSize = 16.sp,
        color = Color.White
    ),
    bodySmall = TextStyle(
        fontFamily = Roboto,
        fontWeight = FontWeight.Normal,
        fontSize = 13.sp
    ),
    labelSmall = TextStyle(
        fontFamily = Roboto,
        fontWeight = FontWeight.W400,
        fontSize = 13.sp
    )
)