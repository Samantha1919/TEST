package com.example.city.ui

import androidx.lifecycle.ViewModel
import com.example.city.data.DataSource
import com.example.city.data.UiState
import com.example.city.model.Category
import com.example.city.model.Recommendation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CityViewModel : ViewModel() {

    val dataSourceInstance: DataSource = DataSource()
    val initialState = UiState(null, emptyList(), null)

//    var test = dataSourceInstance.recommendations.filter { it.type == Category.Restaurant }

    private val _uiState: MutableStateFlow<UiState> =
        MutableStateFlow(initialState) // change la valeur
    val uiState: StateFlow<UiState> =
        _uiState.asStateFlow() // affiche la valeur et apres il faut la prendre pr vrm lafficher

    fun selectRecommendationsCategory(selectedCategory: Category) { // dcp le param va dans la valeur category, chosit la categorie de la recommandation

        val listRecommendationForCategory =
            dataSourceInstance.recommendations.filter { it.type == selectedCategory }


        _uiState.update { currentState ->
            currentState.copy(
                category = selectedCategory,
                recommendations = listRecommendationForCategory
            )
        }
    }

    fun selectRecommendation (recommendation: Recommendation ) {

        _uiState.update { currentState ->
            currentState.copy(
                recommendation = recommendation
            )

        }

    }
}






// choisit le nom dune recommandation de la categorie chosie et affiche le detail de cette recommandation
//    fun selectRecommendationName(recommendationName: Recommendation){
//        _uiState.value = recommendationName
//    }