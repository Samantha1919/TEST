package com.example.city.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.city.data.UiState
import com.example.city.model.Recommendation

@Composable
fun DisplayRecommendationsDetailed(
    modifier: Modifier,
    cityViewModel: CityViewModel,
) {

    val uiState: UiState by cityViewModel.uiState.collectAsState() // affiche
    val recommendation = uiState.recommendation
    if (recommendation != null) {
        RecommendationItem(
            modifier,
            recommendation = recommendation
        )
    }

}

@Composable
fun RecommendationItem(modifier: Modifier, recommendation: Recommendation) {

    Column(
        modifier = modifier
            .fillMaxHeight()
            .padding(40.dp)
    ) {
        Image(painter = painterResource(recommendation.image), contentDescription = null, modifier.height(200.dp))
        Text(text = stringResource(recommendation.name))
        Text(text = stringResource(recommendation.description))
    }

}