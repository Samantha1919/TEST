package com.example.city.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.city.data.UiState
import com.example.city.model.Recommendation

@Composable
fun RecommendationsNameList(
    modifier: Modifier = Modifier,
    cityViewModel: CityViewModel,
    goToRecommendationDetailed: () -> Unit,
) {

    val uiState: UiState by cityViewModel.uiState.collectAsState() // affiche

    LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp), modifier = modifier) {
        items(uiState.recommendations) { recommendation: Recommendation ->
            RecommendationName(recommendation, goToRecommendationDetailed, cityViewModel)
        }

    }

}

@Composable
fun RecommendationName(
    recommandationParam: Recommendation,
    goToRecommendationDetailed: () -> Unit,
    cityViewModel: CityViewModel,
) { // affiche seulement le nom des recommandations
    TextButton(onClick = {
        onRecommandationNameClick(
            cityViewModel = cityViewModel,
            goToRecommendationDetailed = goToRecommendationDetailed,
            recommandationParam = recommandationParam
        )
    }) {
        Text(text = stringResource(recommandationParam.name))
    }

}

fun onRecommandationNameClick(
    cityViewModel: CityViewModel,
    goToRecommendationDetailed: () -> Unit,
    recommandationParam: Recommendation
) {
    cityViewModel.selectRecommendation(recommandationParam)
    goToRecommendationDetailed()
}
