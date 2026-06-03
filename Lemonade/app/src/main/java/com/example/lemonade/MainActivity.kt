package com.example.lemonade

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                val modifierWithPaddings = Modifier.padding(innerPadding)
                Log.d("test", "onCreate: modifierWithPaddings = $modifierWithPaddings")
                Log.d("test", "onCreate: Modifier = $Modifier")

                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = "treeScreen"
                ) {
                    composable("treeScreen") {
                        // On passe un callback pour naviguer
                        TreeScreen(
                            modifier = Modifier.fillMaxSize(),
                            navigateToLemon = { navController.navigate("lemonScreen") }
                        )
                    }
                    composable("lemonScreen") {
                        LemonScreen(
                            onBackClick = { navController.popBackStack() },
                        )
                    }
                }
            }
        }
    }
}