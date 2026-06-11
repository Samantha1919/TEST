package com.example.activities.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Topic(
    @StringRes val name: Int,
    val availableCourses: Int, // le nombre
    @DrawableRes val image: Int
)
