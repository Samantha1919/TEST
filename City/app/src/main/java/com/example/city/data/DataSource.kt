package com.example.city.data

import com.example.city.R
import com.example.city.model.Recommendation
import com.example.city.model.Categories

class DataSource {

    val recommendations = listOf<Recommendation>(
        Recommendation( // cree un objet de la classe Recommendation
            id = 1, R.string.macdo, R.string.macdo_d, R.drawable.macdo,
            Categories.Restaurant
        ),
        Recommendation(
            id = 2, R.string.bk, R.string.bk_d, R.drawable.bk,
            Categories.Restaurant
        ),
        Recommendation(
            id = 3, R.string.wasabi, R.string.wasabi_d, R.drawable.wasabi,
            Categories.Restaurant
        ),
        Recommendation(
            id = 4, R.string.tacos, R.string.tacos_d, R.drawable.tacos,
            Categories.Restaurant
        ),
        Recommendation(
            id = 5, R.string.dominos, R.string.dominos_d, R.drawable.dominos,
            Categories.Restaurant
        ),
        Recommendation(
            id = 6, R.string.arbys, R.string.arbys_d, R.drawable.arbys,
            Categories.Restaurant
        ),
        Recommendation(
            id = 7, R.string.grange, R.string.grange_d, R.drawable.grange,
            Categories.Park
        ),
        Recommendation(
            id = 8, R.string.bourgogne, R.string.bourgogne_d, R.drawable.bourgogne,
            Categories.Park
        ),
        Recommendation(
            id = 9, R.string.anglais, R.string.anglais_d, R.drawable.anglais,
            Categories.Park
        ),
        Recommendation(
            id = 10, R.string.batie, R.string.batie_d, R.drawable.batie,
            Categories.Park
        ),
        Recommendation(
            id = 11, R.string.bourgogne, R.string.bourgogne_d, R.drawable.bourgogne,
            Categories.Park
        ),
        Recommendation(
            id = 12, R.string.bastions, R.string.bastions_d, R.drawable.bastions,
            Categories.Park
        ),

        Recommendation(
            id = 13, R.string.blue, R.string.blue_d, R.drawable.blue,
            Categories.Cinema
        ),
        Recommendation(
            id = 14, R.string.arena, R.string.arena_d, R.drawable.arena,
            Categories.Cinema
        ),
        Recommendation(
            id = 15, R.string.pathe, R.string.pathe_d, R.drawable.pathe,
            Categories.Cinema
        ),
    )
}