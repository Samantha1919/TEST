package com.example.city.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Recommendation(
    val id: Int,
    @StringRes val name: Int,
    @StringRes val description: Int,
    @DrawableRes val image: Int,
    val type: Category
)

enum class Category {
    Restaurant, Park, Cinema
}

