package com.example.city.ui

import android.R.attr.onClick
import android.util.Log.i
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
import com.example.city.model.Categories

// pour chaque categorie afficher un text avec le nom de la categorie
@Composable
fun HomePage(modifier: Modifier) { // la on le met en param

    // la on a toutes les categories
    val categories: List<Categories> =
        Categories.entries // les 2 sont de types EnumEntries<Categories>


    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) { // la on utilise le innerPadding
        items(categories) { category -> CategoryItem(categoryParam = category, onClick = {}/*onClick*/) }
    }

}

@Composable
fun CategoryItem(onClick: () -> Unit, categoryParam: Categories) {
    TextButton(onClick = { /*DisplayRecommandations(category = categoryParam, isShowingHomePage = false )*/ }) {
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

data class UiState(val isShowingHomePage: Boolean = true)


fun saman() {
    val test = UiState()
    test.isShowingHomePage
}


