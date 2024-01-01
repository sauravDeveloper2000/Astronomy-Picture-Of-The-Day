package com.example.astronomypictureoftheday.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.astronomypictureoftheday.R

val FiraSans = FontFamily(
    Font(R.font.firasans_bold, FontWeight.Bold)
)
val Sevillana = FontFamily(
    Font(R.font.sevillana_regular)
)
val Archivo = FontFamily(
    Font(R.font.archivo_extracondensed_medium),
    Font(R.font.archivo_regular)
)
// Set of Material typography styles to start with
val Typography = Typography(
//    bodyLarge = TextStyle(
//        fontFamily = Archivo,
//        fontWeight = FontWeight.Normal,
//        fontSize = 16.sp,
//        lineHeight = 26.sp,
//        letterSpacing = 0.9.sp
//    ), bodyLarge = TextStyle(
//        fontFamily = Archivo,
//        fontWeight = FontWeight.Normal,
//        fontSize = 16.sp,
//        lineHeight = 26.sp,
//        letterSpacing = 0.9.sp
//    ),
    displaySmall = TextStyle(
        fontFamily = FiraSans,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp
    ),
    bodySmall = TextStyle(
        fontFamily = Sevillana,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = Archivo,
        fontWeight = FontWeight.Medium,
        fontSize = 18.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)
