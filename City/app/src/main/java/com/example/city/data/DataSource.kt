package com.example.city.data

import com.example.city.R
import com.example.city.model.Recommendation
import com.example.city.model.Category

class DataSource {

    val recommendations = listOf(
        Recommendation( // cree un objet de la classe Recommendation
            id = 1, R.string.macdo, R.string.macdo_d, R.drawable.macdo,
            Category.Restaurant
        ),
        Recommendation(
            id = 2, R.string.bk, R.string.bk_d, R.drawable.bk,
            Category.Restaurant
        ),
        Recommendation(
            id = 3, R.string.wasabi, R.string.wasabi_d, R.drawable.wasabi,
            Category.Restaurant
        ),
        Recommendation(
            id = 4, R.string.tacos, R.string.tacos_d, R.drawable.tacos,
            Category.Restaurant
        ),
        Recommendation(
            id = 5, R.string.dominos, R.string.dominos_d, R.drawable.dominos,
            Category.Restaurant
        ),
        Recommendation(
            id = 6, R.string.arbys, R.string.arbys_d, R.drawable.arbys,
            Category.Restaurant
        ),
        Recommendation(
            id = 7, R.string.grange, R.string.grange_d, R.drawable.grange,
            Category.Park
        ),
        Recommendation(
            id = 8, R.string.bourgogne, R.string.bourgogne_d, R.drawable.bourgogne,
            Category.Park
        ),
        Recommendation(
            id = 9, R.string.anglais, R.string.anglais_d, R.drawable.anglais,
            Category.Park
        ),
        Recommendation(
            id = 10, R.string.batie, R.string.batie_d, R.drawable.batie,
            Category.Park
        ),
        Recommendation(
            id = 11, R.string.bastions, R.string.bastions_d, R.drawable.bastions,
            Category.Park
        ),

        Recommendation(
            id = 12, R.string.blue, R.string.blue_d, R.drawable.blue,
            Category.Cinema
        ),
        Recommendation(
            id = 13, R.string.arena, R.string.arena_d, R.drawable.arena,
            Category.Cinema
        ),
        Recommendation(
            id = 14, R.string.pathe, R.string.pathe_d, R.drawable.pathe,
            Category.Cinema
        ),
    )
}