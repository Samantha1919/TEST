package com.example.city.ui

import androidx.lifecycle.ViewModel
import com.example.city.data.DataSource
import com.example.city.model.Category
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class CategoriesViewModel : ViewModel() {

    val dataSourceInstance: DataSource = DataSource()

//    var test = dataSourceInstance.recommendations.filter { it.type == Category.Restaurant }

    private val _uiState: MutableStateFlow<Category?> = MutableStateFlow(null) // change
    val uiState: StateFlow<Category?> = _uiState.asStateFlow() // affiche

    fun selectRecommendationsCategory (category: Category) {
        _uiState.value = category
    }
}