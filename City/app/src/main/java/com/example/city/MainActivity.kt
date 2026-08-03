package com.example.city

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.city.ui.DisplayRecommendations
import com.example.city.ui.HomePage
import com.example.city.ui.HomeTopBar
import com.example.city.ui.theme.CityTheme

enum class Routes {
    HomePage, Recommendations
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CityTheme {
                Scaffold(
                    topBar = {
                        HomeTopBar()
                    }, modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    CityApp(
                        modifier = Modifier.padding(innerPadding),
                    )
                }
            }
        }
    }
}

@Composable
fun CityApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Routes.HomePage.name
    )
    {
        composable(route = Routes.HomePage.name) { // dcp ca cest lecran de debut
            HomePage(modifier = modifier)
        }

        composable(route = Routes.Recommendations.name) {
            DisplayRecommendations()
        }

    }
}

@Preview(showBackground = true)
@Composable
fun CityPreview() {
    CityTheme {
        CityApp()
    }
}