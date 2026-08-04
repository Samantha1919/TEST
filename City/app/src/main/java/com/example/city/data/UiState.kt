package com.example.city.data

import com.example.city.model.Category
import com.example.city.model.Recommendation

data class UiState (
    val category: Category?,
    val recommendations: List<Recommendation>,
    val recommendation: Recommendation?
)