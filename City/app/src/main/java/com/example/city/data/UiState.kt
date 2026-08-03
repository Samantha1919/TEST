package com.example.city.data

import com.example.city.model.Recommendation

data class UiState (
    val category: String,
    val recommendation: List<Recommendation>,
    val recommendationId: Int
)