package com.example.thirtydays.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.thirtydays.R

data class Day (
    @DrawableRes
    val imageRessource: Int,

    @StringRes
    val name: Int,

    @StringRes
    val description: Int

)

val days = listOf(
    Day(R.drawable.image_1, R.string.day_one, R.string.day_one_description), // de type Day
    Day(R.drawable.image_2, R.string.day_two, R.string.day_two_description),
    Day(R.drawable.image_3, R.string.day_three, R.string.day_three_description),
    Day(R.drawable.image_4, R.string.day_four, R.string.day_four_description),
    Day(R.drawable.image_5, R.string.day_five, R.string.day_five_description),
    Day(R.drawable.image_6, R.string.day_six, R.string.day_six_description),
    Day(R.drawable.image_7, R.string.day_seven, R.string.day_seven_description),
    Day(R.drawable.image_8, R.string.day_eight, R.string.day_eight_description),
    Day(R.drawable.image_9, R.string.day_nine, R.string.day_nine_description),
    Day(R.drawable.image_10, R.string.day_ten, R.string.day_ten_description),
    )