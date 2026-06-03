package com.example.lemonade

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun TreeScreen(
    modifier: Modifier,
    navigateToLemon: () -> Unit // de type Unit psq ca rnevoie rien ca te fait just epasser udne page a lautre
) {
    Column(
        modifier = modifier.fillMaxSize(), // faut mettre le fill max size pr que ca se centre au milieu de la page
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        var result by remember { mutableStateOf((2..4).random()) }
        var click by remember { mutableStateOf(0) }

        Button(
            modifier = Modifier.background(Color.Red),
            onClick = {
                Log.d("sam", "TreeScreen: $click, $result")
                if (click < result) {
                    click++
                } else {
                    click = 0
                    result = (2..4).random()

                    navigateToLemon()
                }
            },
        ) {
            Image( // cv dans le content du btn
                painter = painterResource(R.drawable.lemon_tree),
                contentDescription = null
            )
        }

        val title = "Tap the lemon tree to select a lemon" // la on lui assigne sa valeur
        Text( // la on affiche la valeur de title
            text = title,
            modifier = Modifier,
            textAlign = TextAlign.Center
        )

        // Log.d("test", "TreeScreen: $name")
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    TreeScreen(
        modifier = Modifier,
        navigateToLemon = {} // on appelle une f lamba vide comme ca on rajoute r en param mais ca fait le comportement dmd
    )
}
