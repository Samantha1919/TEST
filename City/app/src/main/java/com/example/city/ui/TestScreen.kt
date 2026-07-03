package com.example.city.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

enum class Routes {
    Bonjour, Aurevoir, Salut
}

@Composable
fun TestScreen(modifier: Modifier = Modifier, navController: NavHostController) { // exemple de 2 facons diff de faire et la cest sans le next

    Row() {

        Button(
            onClick = { navController.navigate(Routes.Aurevoir.name) },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Blue)
        ) {
            Text(text = "Bonjour")
        }

        Button(
            onClick = { navController.navigate(Routes.Aurevoir.name) },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
        ) {
            Text(text = "Aurevoir")
        }

        Button(
            onClick = { navController.navigate(Routes.Salut.name) },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
        ) {
            Text(text = "Salut")
        }
    }
}

@Composable
fun Action(next: () -> Unit = {}) {
    Button(onClick = next) {
        Text(text = "Aurevoir")
    }
}

@Composable
fun Salut(
    next: () -> Unit = {}
) {
    Button(onClick = next) {
        Text(text = "Salut")
    }
}

@Composable
fun Application(navController: NavHostController = rememberNavController()) {
    NavHost(
        // gere ce qui saffiche selon la route avec le param navController
        navController = navController, // controle la route ou tu te trouves actuellement
        startDestination = Routes.Bonjour.name, // debut
    )
    {
        composable(route = Routes.Bonjour.name) { // dcp c lecran de debut de la nav
            TestScreen(navController = navController)
        }

        composable(route = Routes.Aurevoir.name) {
            Action(next = { navController.navigate(Routes.Salut.name) })
        }

        composable(route = Routes.Salut.name) {
            Salut(next = { navController.navigate(Routes.Bonjour.name) })
        }

    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    MaterialTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            Application()
        }
    }
}