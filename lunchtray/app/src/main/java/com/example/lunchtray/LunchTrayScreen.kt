package com.example.lunchtray

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.lunchtray.datasource.DataSource
import com.example.lunchtray.ui.AccompanimentMenuScreen
import com.example.lunchtray.ui.CheckoutScreen
import com.example.lunchtray.ui.EntreeMenuScreen
import com.example.lunchtray.ui.OrderViewModel
import com.example.lunchtray.ui.SideDishMenuScreen
import com.example.lunchtray.ui.StartOrderScreen

// TODO: Screen enum en PREMIER

enum class LunchTrayScreen(@StringRes val title: Int) {
    Start(title = R.string.app_name),
    Entree(title = R.string.choose_entree),
    SideDish(title = R.string.choose_side_dish),
    Accompaniment(title = R.string.choose_accompaniment),
    Checkout(title = R.string.order_checkout)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LunchTrayBar(currentScreen: LunchTrayScreen) {
    CenterAlignedTopAppBar(
        title = {
            Text(stringResource(currentScreen.title)) // psq les titles sont dans strings.xml
        })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LunchTrayApp(
    viewModel: OrderViewModel = viewModel(),
    navController: NavHostController = rememberNavController() // cree le navController comme ca on peut lutiliser et on le cree dans les param au lieu de creer 1 variable
) {

//    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState() // sert a afficher dcp le titre de lecran courant et si y en a aucun selectionné c Start le title
    val currentScreen = LunchTrayScreen.valueOf(
        backStackEntry?.destination?.route ?: LunchTrayScreen.Start.name
    )

    Scaffold(
        topBar = {
            LunchTrayBar(currentScreen = currentScreen)
        }
    ) { innerPadding ->
        val uiState by viewModel.uiState.collectAsState()

        // TODO: Navigation host en 2ème et les composable en 3eme

        NavHost(
            navController = navController,
            startDestination = LunchTrayScreen.Start.name,
            modifier = Modifier.padding(innerPadding)
        ) {

            composable(route = LunchTrayScreen.Start.name) {
                StartOrderScreen(onStartOrderButtonClicked = { // lambda entre {} facon decrire une fonction entre {} sans la nommer
                    navController.navigate(LunchTrayScreen.Entree.name)
                })
            }

            composable(route = LunchTrayScreen.Entree.name) {
                EntreeMenuScreen(
                    onCancelButtonClicked = {
                        navController.navigate(LunchTrayScreen.Start.name)
                    },
                    onNextButtonClicked = {
                        navController.navigate(LunchTrayScreen.SideDish.name)
                    },
                    onSelectionChanged = { entree -> // c un lambda
                        viewModel.updateEntree(entree) // on peut ausis appeler ca item
                    },
                )
            }

            composable(route = LunchTrayScreen.SideDish.name) {
                SideDishMenuScreen(
                    options = DataSource.sideDishMenuItems,
                    onCancelButtonClicked = {
                        navController.navigate(LunchTrayScreen.Start.name)
                    },
                    onNextButtonClicked = {
                        navController.navigate(LunchTrayScreen.Accompaniment.name)
                    },
                    onSelectionChanged = { side ->
                        viewModel.updateSideDish(side)
                    },
                )
            }

            composable(route = LunchTrayScreen.Accompaniment.name) {
                AccompanimentMenuScreen(
                    options = DataSource.accompanimentMenuItems,
                    onCancelButtonClicked = {
                        navController.navigate(LunchTrayScreen.Start.name)
                    },
                    onNextButtonClicked = {
                        navController.navigate(LunchTrayScreen.Checkout.name)
                    },
                    onSelectionChanged = { accompaniment ->
                        viewModel.updateAccompaniment(accompaniment)
                    },
                )
            }

            composable(route = LunchTrayScreen.Checkout.name) {
                CheckoutScreen(
                    orderUiState = uiState,
                    onCancelButtonClicked = {
                        navController.navigate(LunchTrayScreen.Start.name)
                    },
                    onNextButtonClicked = {
                        navController.navigate(LunchTrayScreen.Start.name)
                    },

                    )
            }
        }
    }
}