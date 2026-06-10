package com.example.bjr

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bjr.ui.theme.ArtGalleryTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArtGalleryTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Affichage(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Affichage(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(), // on lui met fillMaxSize pour quil prenne toute la place pr quil centre vrm tout
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val textes = listOf("Cookie", "Vera", "Rico")
        val images = listOf(R.drawable.cat_one, R.drawable.cat_two, R.drawable.cat_tree)
        var indexPrParcourirLeTableau by remember { mutableIntStateOf(0) }
        val maxIndex =
            images.size - 1 // psq ducoup sa size est egale a 3 et vu que dans un array le premier index est egal a 0 bah ca fait 2 elements

        Text(
            text = "Cats gallery",
            fontSize = 25.sp,
            modifier = modifier
        )

        Image(
            painter = painterResource(images[indexPrParcourirLeTableau]),
            contentDescription = null,
            modifier = Modifier.padding(bottom = 20.dp)
        )

        Text(
            text = textes[indexPrParcourirLeTableau],
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 20.dp)
        )

        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth() // pour que la row prenne la largeur max et comme ca apres tu peux bouger les boutons car tu as la place
        ) {
            Button(onClick = {
                if (indexPrParcourirLeTableau == 0) {
                    indexPrParcourirLeTableau = maxIndex
                } else {
                    indexPrParcourirLeTableau--
                }
            }) {
                Text(text = "Previous")
            }

            Button(onClick = {
                if (indexPrParcourirLeTableau == maxIndex) {
                    indexPrParcourirLeTableau = 0

                } else {
                    indexPrParcourirLeTableau++
                }
            }) {
                Text(text = "Next")
            }

        }
    }
}



