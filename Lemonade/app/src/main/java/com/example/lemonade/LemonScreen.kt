package com.example.lemonade

import android.R.attr.text
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
fun LemonScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit
) {
    val images = listOf(R.drawable.lemon_squeeze, R.drawable.lemon_drink)
    val textes : List<String> = listOf("test", "deucie", "troisieme")


    var indexPrParcourirLeTableau by remember { mutableStateOf(0) } // on commence a 0 psq c lindex de limage dans le tableau et a chaque fois ca va recomposer la page avce limage dmd

    Column(
        modifier = modifier.fillMaxSize(), // faut mettre le fill max size pr que ca se centre au milieu de la page
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {

        var result by remember { mutableStateOf(1) }
        result = (2..4).random()

        var click = 0

        Button(
            modifier = Modifier.background(Color.Blue),
            onClick = {
                click++
                Log.d("sam", "Nombre de click : $click, random : $result")
                if (click >= result){
                    click = 0
                    result = (2..4).random()
                    indexPrParcourirLeTableau++
                }
            },
        ) {
            Image( // cv dans le content du btn
                painter = painterResource(images[indexPrParcourirLeTableau % images.size]), // pr quon soit tjrs dans la taille du tableau -1 (psq le 1er index est egal a 0)
                contentDescription = null
            )
        }

        val title = "Tap the lemon tree to select a lemon" // la on lui assigne sa valeur
        Text(
            text = textes[indexPrParcourirLeTableau%textes.size],
            modifier = Modifier,
            textAlign = TextAlign.Center
        ) // la on affiche la valeur de title

        // Log.d("test", "TreeScreen: $name")
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    TreeScreen(
        modifier = Modifier,
        navigateToLemon = {}
    )
}