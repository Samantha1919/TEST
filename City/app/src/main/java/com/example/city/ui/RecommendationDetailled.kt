package com.example.city.ui

import android.util.Log.v
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.city.data.UiState
import com.example.city.model.Recommendation

@Composable
fun DisplayRecommendationsDetailed(
    modifier: Modifier,
    cityViewModel: CityViewModel,
) {

    val uiState: UiState by cityViewModel.uiState.collectAsState() // affiche
    val recommendation = uiState.recommendation
    if ( recommendation != null) {
        RecommendationItem(
            modifier,
            recommendation = recommendation
        )
    }

}

@Composable
fun RecommendationItem(modifier: Modifier, recommendation: Recommendation) {

    Column(modifier = modifier.fillMaxHeight(),
        verticalArrangement = Arrangement.SpaceEvenly) {
        Image(painter = painterResource(recommendation.image), contentDescription = null)
        Text(text = stringResource(recommendation.name))
        Text(text = stringResource(recommendation.description))
    }

}