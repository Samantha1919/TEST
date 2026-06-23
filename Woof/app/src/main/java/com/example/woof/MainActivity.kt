package com.example.woof

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.woof.data.Dog
import com.example.woof.data.dogs
import com.example.woof.ui.theme.WoofTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WoofTheme(darkTheme = false) {
                Scaffold(topBar = { WoofTopAppBar() }) { innerPadding ->
                    Surface(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {
                        WoofApp()
                    }
                }

            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class) // psq CenterAlignedTopAppBarc un truc experimental
@Composable
fun WoofTopAppBar(modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier
                        .size(dimensionResource(id = R.dimen.image_size))
                        .padding(dimensionResource(id = R.dimen.padding_small)),
                    painter = painterResource(R.drawable.ic_woof_logo),

                    contentDescription = null
                )
                Text(
                    text = stringResource(R.string.app_name),
                    style = MaterialTheme.typography.displayLarge
                )
            }
        },
        modifier = modifier
    )
}

@Composable
fun WoofApp() {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.padding(6.dp)
    ) {
        items(dogs) {
            DogItem(dog = it) // le it tu peux le remplacer par dog cv dire la mm chose

        }
    }
}

@Composable
fun DogItem(
    dog: Dog,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioNoBouncy,
                    stiffness = Spring.StiffnessMedium
                )
                )
            ) {

                Row(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(dimensionResource(R.dimen.padding_small)) // ca le convertit dcp c comme si ct 8.dp
                ) {
                    DogImage(dog.imageResourceId) // la ou on recup les noms et tt avec le .
                    DogInformation(dog.name, dog.age)
                    Spacer(modifier = Modifier.weight(1f)) // espace pour que la fleche soit au bon endroit icon
                    DogArrowIcon(
                        expandedParam = expanded,
                        onClick = {
                            expanded = !expanded
                        } // expanded passe a la valeur contraire au debut il est a false apres tu appuies et dcp il est a true ect
                    )
                }

                if (expanded) { // si expanded est a true, montre les hobbies des chiens
                    DogHobby(dog.hobbies)
                }

            }
    }
}

@Composable
fun DogHobby(
    @StringRes dogHobby: Int,
) {
    Column() {
        Text(
            text = "About:",
            style = MaterialTheme.typography.labelSmall,
            modifier = Modifier.padding(
                start = dimensionResource(R.dimen.padding_medium),
                top = dimensionResource(R.dimen.padding_small),
                end = dimensionResource(R.dimen.padding_medium),

                )
        )
        Text(
            text = stringResource(dogHobby),
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(
                start = dimensionResource(R.dimen.padding_medium),
                end = dimensionResource(R.dimen.padding_medium),
                bottom = dimensionResource(R.dimen.padding_medium)
            )
        )

    }
}

@Composable
private fun DogArrowIcon( // fleche icone
    expandedParam: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    IconButton(onClick = onClick, modifier = Modifier) {
        Icon(
            imageVector = if (expandedParam) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore, // si c a true affiche la fleche vers le haut et si c a false affiche la fleche vers le bas
            contentDescription = null,
            tint = MaterialTheme.colorScheme.secondary
        )

    }

}

@Composable
fun DogImage( // image chien
    @DrawableRes dogIcon: Int, // juste un nom et apres on recup vrm
    modifier: Modifier = Modifier
) {
    Image(
        modifier = modifier
            .size(dimensionResource(R.dimen.image_size))
            .padding(dimensionResource(R.dimen.padding_small))
            .clip(MaterialTheme.shapes.small), // clip ca affiche une image découpée selon la forme quon a faite dcp
        contentScale = ContentScale.Crop, // comme ca toutes les photos sont arrondis pareil
        painter = painterResource(dogIcon),
        contentDescription = null
    )
}

@Composable
fun DogInformation(
    @StringRes dogName: Int,
    dogAge: Int,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = stringResource(dogName), // on recup le nom des chiens
            style = MaterialTheme.typography.displayMedium,
            modifier = Modifier.padding(top = dimensionResource(R.dimen.padding_small))
        )
        Text(
            text = stringResource( // le premier cest pr ecrire year old avec %d et apres cest lage, dcp ca met le chiffre dans le %d ca le remplace
                R.string.years_old,
                dogAge
            ),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Preview
@Composable
fun WoofPreview() {
    WoofTheme(darkTheme = true) {

        Scaffold(topBar = { WoofTopAppBar() }) { innerPadding ->
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                WoofApp()
            }
        }

    }

}
