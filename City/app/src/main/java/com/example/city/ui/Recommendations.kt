package com.example.city.ui

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import com.example.city.data.DataSource
import com.example.city.model.Categories
import com.example.city.model.Recommendation


@Composable
fun DisplayRecommandations(category : Categories,isShowingHomePage: UiState){

    val recommendationInstance: DataSource = DataSource()

//    recommendationInstance.recommendations.filter {  }


    LazyColumn() { }
}