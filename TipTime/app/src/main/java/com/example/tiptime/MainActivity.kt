package com.example.tiptime

import java.text.NumberFormat
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.annotation.VisibleForTesting
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
fun PlanTexte(
    modifier: Modifier = Modifier
) {

    var montantFacture: String by remember { // pk on lui donne une valeur de string c psq on va lafficher dans une string ?, chaque fois que sa valeur change compose va faire une recomposition
        mutableStateOf("")
    }

    var pourboireInput by remember { mutableStateOf("") }

    val montant = montantFacture.toDoubleOrNull()
        ?: 0.0 // convertir la String en Double, analyse le variable en tant que Double et retourne le resultat ou 0.0 si c pas un nombre dcp ca retourne null et on gere pr repondre 0.0 avec le elvis operator ?:

    val pourboirePourcent = pourboireInput.toDoubleOrNull() ?: 0.0

    var roundUp by remember { mutableStateOf(false) } // false c letat par defaut

    val pourboire = calculateTip(montant, pourboirePourcent, roundUp)


    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .statusBarsPadding()
            .padding(horizontal = 40.dp)
            .safeDrawingPadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Calculate Tip",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(bottom = 16.dp, top = 40.dp)
                .align(alignment = Alignment.Start)
        )
        EditNumberField(
            value = montantFacture,
            onValueChange = {
                montantFacture =
                    it // it est la nvlle valeur du texte/montantPourboire dcp cest ca qui affiche le texte noté
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            leadingIcon = R.drawable.money,
            label = R.string.prix
        )
        EditNumberField(
            value = pourboireInput,
            onValueChange = { pourboireInput = it },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number), // ou keyboardOptions = KeyboardOptions.Default.copy( keyboardType = KeyboardType.Number, imeAction = ImeAction.Done) mais les 2 ont la mm chose
            label = R.string.pourcentage_pourboire,
            leadingIcon = R.drawable.percent
        )

        ArrondirPourboire(
            roundUp = roundUp,
            onRoundUpChanged = { roundUp = it },
            modifier = Modifier.padding(bottom = 32.dp)
        )

        Text(
            text = stringResource(
                R.string.montant_pourboire,
                pourboire
            ), // on affiche le pourboire la valeur comme ca %s
            style = MaterialTheme.typography.displaySmall
        )

        Spacer(modifier = Modifier.height(150.dp))

    }
}

@Composable
fun EditNumberField(
    modifier: Modifier = Modifier,
    @DrawableRes leadingIcon: Int,
    onValueChange: (String) -> Unit, // afin que letat puisse etre maj quand luser ecrit
    value: String,
    keyboardOptions: KeyboardOptions,
    @StringRes label: Int // pk c un int ?
) {

    TextField(
        value = value, // pas bs de .value car on a utilisé by, value cest le texte qui va afficher la valeur du texte quoi,
        onValueChange = onValueChange, //  onValueChange cest le rappel lambda qui est déclenché lorsquon saisit du texte dans le TextField
        singleLine = true, // ca met tt sur une ligne meme si le texte est long
        label = { Text(stringResource(label)) },
        leadingIcon = { Icon(painter = painterResource(id = leadingIcon), null) },
        keyboardOptions = keyboardOptions, // si le clavier souvre ouvre un clavier de num mais faut lactiver sur le tel
        modifier = Modifier
            .padding(bottom = 32.dp)
            .fillMaxWidth()
    )

}

@Composable
fun ArrondirPourboire(
    modifier: Modifier = Modifier,
    roundUp: Boolean,
    onRoundUpChanged: (Boolean) -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .size(48.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "Round up tip ?")
        Switch(
            checked = roundUp, // regarde si cest coché ou pas, état/state de Switch
            onCheckedChange = onRoundUpChanged, // appelé en cas de clic sur le bouton
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(
                    Alignment.End
                )
        )
    }
}

@VisibleForTesting
internal fun calculateTip(
    montantFacture: Double,
    pourboirePourcent: Double, // jai enlevé la valeur par defaut
    roundUp: Boolean
): String {
    var pourboire = pourboirePourcent / 100 * montantFacture
    if (roundUp) {
        pourboire = kotlin.math.ceil(pourboire) // arrondit le pourboire au chiffre superieur
    }
    return NumberFormat.getCurrencyInstance() // NumberFormat apllique un format monétaire
        .format(pourboire)
}

//@Preview(showBackground = true)
//@Composable


