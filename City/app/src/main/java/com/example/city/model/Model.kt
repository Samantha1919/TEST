package com.example.city.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import java.sql.Types

data class Recommendation(
    val id: Int,
    @StringRes val name: Int,
    @StringRes val description: Int,
    @DrawableRes val image: Int,
    val type: Categories
)

enum class Categories {
    Restaurant, Park, Cinema
}