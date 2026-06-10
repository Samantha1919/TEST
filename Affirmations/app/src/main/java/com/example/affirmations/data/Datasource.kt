package com.example.affirmations.data

import com.example.affirmations.model.Affirmation
import androidx.compose.ui.res.stringResource
import com.example.affirmations.R

class Datasource() {
    fun loadAffirmations(): List<Affirmation> {
        return listOf<Affirmation>(
            Affirmation(R.string.affirmation1, R.drawable.cat_1),
            Affirmation(R.string.affirmation2, R.drawable.cat_2),
            Affirmation(R.string.affirmation3, R.drawable.cat_3),
            Affirmation(R.string.affirmation4, R.drawable.cat_4),
            Affirmation(R.string.affirmation5, R.drawable.cat_5),
            Affirmation(R.string.affirmation6, R.drawable.cat_6),
            Affirmation(R.string.affirmation7, R.drawable.cat_7),
            Affirmation(R.string.affirmation8, R.drawable.cat_8),
            Affirmation(R.string.affirmation9, R.drawable.cat_9),
            Affirmation(R.string.affirmation10, R.drawable.cat_10)
        )
    }
}
