package com.developersbreach.cutepuppies.utils

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp

// Applies gradient background for the row items in cat list.
fun Modifier.offsetGradientBackground(
    colorList: List<Color>,
    width: Float
) = background(
    Brush.verticalGradient(
        colorList,
        tileMode = TileMode.Clamp,
        startY = 0f,
        endY = width,
    )
)

// Gets the width for the gradient to apply.
val gradientWidth: Float
    @Composable
    get() = with(LocalDensity.current) {
        (2 * (180.dp).toPx())
    }
