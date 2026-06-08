package com.example.tiptime

import android.icu.text.NumberFormat
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.tiptime.ui.theme.TipTimeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TipTimeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    PlanTexte(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun PlanTexte(modifier: Modifier) {
    Column(
        modifier = Modifier
            .statusBarsPadding()
            .padding(horizontal = 40.dp)
            .safeDrawingPadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Calculate Tip",
            modifier = Modifier
                .padding(bottom = 16.dp, top = 40.dp)
                .align(alignment = Alignment.Start)
        )
        EditNumberField()

        Text(
            text = stringResource(R.string.montant_pourboire, "$0.00"),
            style = MaterialTheme.typography.displaySmall
        )

        Spacer(modifier = Modifier.height(150.dp))

    }
}

@Composable
fun EditNumberField(modifier: Modifier = Modifier) {
    var montantPourboire: String by remember { // pk on lui donne une valeur de string c psq on va lafficher dans une string ?, chaque fois que sa valeur change compose va faire une recomposition
        mutableStateOf("")
    }

    TextField(
        value = montantPourboire , // pas bs de .value car on a utilisé by
        onValueChange = {montantPourboire = it}, // it est la nvlle valeur du texte/montantPourboire dcp cest ca qui affiche le texte noté
        modifier = Modifier
            .padding(bottom = 32.dp)
            .fillMaxWidth()
    ) // value cest le texte qui va afficher la valeur du texte quoi, onValueChange cest le rappel lambda qui est déclenché lorsquon saisit du texte dans le TextField
}

private fun calculateTip(
    amount: Double,
    tipPercent: Double = 15.0 // pr linstant le pourboire est de 15%
): String {
    val tip = tipPercent / 100 * amount
    return NumberFormat.getCurrencyInstance().format(tip)
}

//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    TipTimeTheme {
//        Greeting("Android")
//    }
//}