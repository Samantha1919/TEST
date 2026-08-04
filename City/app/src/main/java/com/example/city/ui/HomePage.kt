package com.example.city.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.city.R
import com.example.city.model.Category

// pour chaque categorie afficher un text avec le nom de la categorie
@Composable
fun HomePage(
    goToRecommendationList: () -> Unit,
    modifier: Modifier,
    cityViewModel: CityViewModel,
) {
    // dcp la HomeTopBar() saffiche sur la page mais pas sur la preview de MainActivity ?

    // la on a toutes les categories
    val categories: List<Category> =
        Category.entries // les 2 sont de types EnumEntries<Categories> ou List en mode

    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) { // la on utilise le innerPadding
        items(categories) { category ->
            CategoryItem(
                categoryParam = category,
                cityViewModel,
                goToRecommendationList
            )
        }
    }
    // on a deja veirife si ct null avant mais ducoup c impossible quil soit null
}

@Composable
fun CategoryItem(
    categoryParam: Category,
    cityViewModel: CityViewModel,
    goToRecommendationList: () -> Unit,

    ) {
    TextButton(onClick = {
        onCategoryClick(
            goToRecommendationList,
            cityViewModel,
            categoryParam,
        )
    }) { // DisplayRecommendations(category = categoryParam )
        Text(text = categoryParam.toString())
    }
}

fun onCategoryClick(
    goToRecommendationList: () -> Unit,
    cityViewModel: CityViewModel,
    categoryParam: Category,
) {
    cityViewModel.selectRecommendationsCategory(categoryParam)
    goToRecommendationList()
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun HomeTopBar() {
    CenterAlignedTopAppBar(title = {
        Text(
            text = stringResource(R.string.app_name),
        )
    })
}



