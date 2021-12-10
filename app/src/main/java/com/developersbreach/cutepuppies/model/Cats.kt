package com.developersbreach.cutepuppies.model

import androidx.compose.ui.graphics.Color

data class Cats(
    val catId: Int,
    val catImage: Int,
    val catName: String,
    val catAge: Int,
    val catDateOfBirth: String,
    val catGender: String,
    val catHairColor: Color,
    val catEyeColor: Color,
    val catDescription: String,
    val catBreed: String,
    val catAbout: String,
)
