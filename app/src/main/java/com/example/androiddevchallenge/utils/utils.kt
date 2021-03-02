/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge.utils

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
