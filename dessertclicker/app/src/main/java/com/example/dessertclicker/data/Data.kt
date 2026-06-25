package com.example.dessertclicker.data

import com.example.dessertclicker.R
//import com.example.dessertclicker.R
import com.example.dessertclicker.model.Dessert

/**
 * [Datasource] generates a list of [Dessert]
 */
object Datasource {
    val dessertList = listOf(
        Dessert(R.drawable.cupcake, 5, 0),
        Dessert(R.drawable.donut, 10, 5),
        Dessert(R.drawable.eclair, 15, 20),
        Dessert(R.drawable.froyo, 30, 30),
        Dessert(R.drawable.gingerbread, 50, 31),
        Dessert(R.drawable.honeycomb, 100, 32),
        Dessert(R.drawable.icecreamsandwich, 500, 33),
        Dessert(R.drawable.jellybean, 1000, 34),
        Dessert(R.drawable.kitkat, 2000, 35),
        Dessert(R.drawable.lollipop, 3000, 36),
        Dessert(R.drawable.marshmallow, 4000, 37),
        Dessert(R.drawable.nougat, 5000, 38),
        Dessert(R.drawable.oreo, 6000, 39)
    )
}