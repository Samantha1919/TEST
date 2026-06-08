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
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.input.KeyboardType
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

    var montantPourboire: String by remember { // pk on lui donne une valeur de string c psq on va lafficher dans une string ?, chaque fois que sa valeur change compose va faire une recomposition
        mutableStateOf("")
    }

    val montant = montantPourboire.toDoubleOrNull()
        ?: 0.0 // convertir la String en Double, analyse le variable en tant que Double et retourne le resultat ou 0.0 si c pas un nombre dcp ca retourne null et on gere pr repondre 0.0 avec le elvis operator ?:
    val pourboire = calculateTip(montant)

    Column(
        modifier = modifier
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
        EditNumberField(value = montantPourboire, onValueChange = { montantPourboire = it }) // it est la nvlle valeur du texte/montantPourboire dcp cest ca qui affiche le texte noté

        Text(
            text = stringResource(R.string.montant_pourboire, pourboire), // on affiche le pourboire la valeur comme ca100
            style = MaterialTheme.typography.displaySmall
        )

        Spacer(modifier = Modifier.height(150.dp))

    }
}

@Composable
fun EditNumberField(
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit, // afin que letat puisse etre maj quand luser ecrit
    value: String
) {

    TextField(
        value = value, // pas bs de .value car on a utilisé by
        onValueChange = onValueChange,
        singleLine = true, // ca met tt sur une ligne meme si le texte est long
        label = { Text("Bill amount") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number), // si le clavier souvre ouvre un clavier de num mais faut lactiver sur le tel
        modifier = Modifier
            .padding(bottom = 32.dp)
            .fillMaxWidth()
    ) // value cest le texte qui va afficher la valeur du texte quoi, onValueChange cest le rappel lambda qui est déclenché lorsquon saisit du texte dans le TextField
}

private fun calculateTip(
    montantPourboire: Double,
    tipPercent: Double = 15.0 // pr linstant le pourboire est de 15%
): String {
    val pourboire = tipPercent / 100 * montantPourboire
    return NumberFormat.getCurrencyInstance() // NumberFormat apllique un format monétaire
        .format(pourboire)
}

//@Preview(showBackground = true)
//@Composable


