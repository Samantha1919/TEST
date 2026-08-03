package com.example.city.ui

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.city.data.DataSource

@Composable
fun DisplayRecommendations(){

    val dataSourceInstance: DataSource = DataSource()

//    dataSourceInstance.recommendations.filter {  }
    LazyColumn() { }
}