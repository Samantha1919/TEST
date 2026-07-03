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
import com.example.city.ui.HomePage
import com.example.city.ui.HomeTopBar
import com.example.city.ui.theme.CityTheme

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
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun CityApp(modifier: Modifier = Modifier) {
    HomePage(modifier = modifier)
}

@Preview(showBackground = true)
@Composable
fun CityPreview() {
    CityTheme {
        CityApp()
    }
}