package com.example.city.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.city.R
import com.example.city.data.DataSource
import com.example.city.model.Category
import com.example.city.model.Recommendation

// pour chaque categorie afficher un text avec le nom de la categorie
@Composable
fun HomePage(
    modifier: Modifier,
    categoriesViewModel: CategoriesViewModel = viewModel(),
) {
    // dcp la HomeTopBar() saffiche sur la page mais pas sur la preview de MainActivity ?

    // la on a toutes les categories
    val categories: List<Category> =
        Category.entries // les 2 sont de types EnumEntries<Categories> ou List en mode

    val selectedCategory: Category? by categoriesViewModel.uiState.collectAsState() // affiche

    when (val category = selectedCategory) {
        null ->
            LazyColumn(
                modifier = modifier,
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) { // la on utilise le innerPadding
                items(categories) { category ->
                    CategoryItem(categoryParam = category, categoriesViewModel)
                }
            }

        else -> ListeRestaurants(
            modifier = modifier,
            category
        ) // on a deja veirife si ct null avant mais ducou c impossible et ca marceh dutiliser category
    }

}

@Composable
fun ListeRestaurants(modifier: Modifier = Modifier, selectedCategory: Category) {
    val dataSourceInstance: DataSource = DataSource()

    var restaurantsRecommendations =
        dataSourceInstance.recommendations.filter { it.type == selectedCategory }


    LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp), modifier = modifier) {
        items(restaurantsRecommendations) { restaurantRecommandation: Recommendation ->
            RestaurantItem(restaurantRecommandation)
        }
    }
}

@Composable
fun RestaurantItem(restaurantRecommandation: Recommendation) {

    Text(text = stringResource(restaurantRecommandation.name))
    Text(text = stringResource(restaurantRecommandation.description))
    Image(painter = painterResource(restaurantRecommandation.image), contentDescription = null)

}

@Composable
fun CategoryItem(
    categoryParam: Category,
    categoriesViewModel: CategoriesViewModel

) {
    TextButton(onClick = { categoriesViewModel.selectRecommendationsCategory(categoryParam) }) { // DisplayRecommendations(category = categoryParam )
        Text(text = categoryParam.toString())
    }
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



