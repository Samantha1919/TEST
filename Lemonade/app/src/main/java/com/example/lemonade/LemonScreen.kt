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
fun LemonScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit
) {
    val images = listOf(R.drawable.lemon_squeeze, R.drawable.lemon_drink, R.drawable.lemon_restart)
    val textes: List<String> = listOf("test", "deucie", "troisieme")

    var indexPrParcourirLeTableau by remember { mutableStateOf(0) } // on commence a 0 psq c lindex de limage dans le tableau et a chaque fois ca va recomposer la page avce limage dmd

    Column(
        modifier = modifier.fillMaxSize(), // faut mettre le fill max size pr que ca se centre au milieu de la page
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {

        var click by remember { mutableStateOf(0) } // commence par 0 vu que c un index dans le tableau
        var result by remember { mutableStateOf((2..4).random()) } // on cree direct une valeur random et on la met en rembember

        Button(
            modifier = Modifier.background(Color.Blue),
            onClick = {
                click++
                Log.d(
                    "sam",
                    "Nombre de click : $click, random : $result, $indexPrParcourirLeTableau"
                )

                if (click >= result) {
                    click = 0
                    result = (2..4).random()
                    indexPrParcourirLeTableau++
                }
                if (indexPrParcourirLeTableau >=  images.size) {
                    onBackClick()  // si indexPrParcourirLeTableau est plus grand que 3 ca revient sur la page
                    return@Button // stop le programme ici pr pas afficher lecran avec un index + grand que 3 (pr image et texte) dcp sans image ecran blanc, dcp pas bs de re verifier lindex
                }

            },
        ) {

            Image(  // cv dans le content du btn
                painter = painterResource(images[indexPrParcourirLeTableau.takeIf { it < 3 } ?: 2]),
                contentDescription = null
            )
        }


        Text(
            text = textes[indexPrParcourirLeTableau.takeIf { it < 3 } ?: (images.size - 1)], // on fait le if sur indexPrParcourirLeTableau, -1 car le premier index est egal a 0, dcp si c plus petit que 3 on prend le chiffre que cest de base et si c plus grand que 3 on prend limage size - 1 ducoup si on arrive a la 4eme image cv nous mettre sur la 3eme mais on sen fiche vu quon change de page apres cv remettre limage avec lindex 0
            modifier = Modifier,
            textAlign = TextAlign.Center
        )

        // Log.d("test", "TreeScreen: $name")
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    LemonScreen(
        modifier = Modifier,
        onBackClick = {}
    )
}