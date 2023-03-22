package com.neobrahma.portfolio.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val portfolioTypography = Typography(
    subtitle1 = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
        letterSpacing = 0.15.sp
    ),
    subtitle2 = TextStyle(
        fontWeight = FontWeight.Light,
        fontSize = 14.sp,
        letterSpacing = 0.1.sp
    ),
    body2 = TextStyle(
        fontWeight = FontWeight.ExtraLight,
        fontSize = 14.sp,
        letterSpacing = 0.25.sp
    )

)