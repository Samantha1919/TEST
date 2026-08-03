package com.example.city.ui
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
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
import com.example.city.Routes

enum class Routess {
    Bonjour, Aurevoir, Salut
}

@Composable
fun Bonjour(
    modifier: Modifier = Modifier,
    navController: NavHostController
) { // exemple de 2 facons diff de faire et la cest sans le next

    Row() {

//        Button(
//            onClick = { navController.navigate(Routes.Aurevoir.name) },
//            colors = ButtonDefaults.buttonColors(containerColor = Color.Blue)
//        ) {
//            Text(text = "Bonjour")
//        }

        Button(
            onClick = { navController.navigate(Routess.Aurevoir.name) },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
        ) {
            Text(text = "Aurevoir")
        }

        Button(
            onClick = { navController.navigate(Routess.Salut.name) },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
        ) {
            Text(text = "Salut")
        }

        Button(onClick = {}) {
            Text(text = "chifrre")
        }
    }
}

@Composable
fun Aurevoir(goToSalut: () -> Unit = {}, goToBonjour: () -> Unit = {}) {
    Row() {
//    Button(onClick = goToAurevoir) {
//        Text(text = "Aurevoir")
//    }
        Button(onClick = goToSalut) {
            Text(text = "Salut")
        }
        Button(onClick = goToBonjour) {
            Text(text = "Bonjour")
        }
    }
}

@Composable
fun Salut(
    goToBonjour: () -> Unit = {}, goToAurevoir: () -> Unit = {}
) {
//    Button(onClick = next) {
//        Text(text = "Salut")
//    }

    Row() {

        Button(onClick = goToBonjour) {
            Text(text = "Bonjour")
        }
        Button(onClick = goToAurevoir) {
            Text(text = "Aurevoir")
        }


    }
}


@Composable
fun Application(navController: NavHostController = rememberNavController()) {
    NavHost(
        // gere ce qui saffiche selon la route avec le param navController
        navController = navController, // controle la route ou tu te trouves actuellement
        startDestination = Routess.Bonjour.name, // debut
    )
    {
        composable(route = Routess.Bonjour.name) { // dcp c lecran de debut de la nav
            Bonjour(navController = navController)
        }

        composable(route = Routess.Aurevoir.name) {
            Aurevoir(
                goToSalut = { navController.navigate(Routess.Salut.name) },
                goToBonjour = { navController.navigate(Routess.Bonjour.name) })
        }

        composable(route = Routess.Salut.name) {
            Salut(goToBonjour = { navController.navigate(Routess.Bonjour.name) }, goToAurevoir = {
                navController.navigate(
                    Routess.Aurevoir.name
                )
            })
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