package com.example.superheroes

import SuperheroesTheme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.superheroes.model.Hero
import com.example.superheroes.model.SuperHeroesDataSource.heroes

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SuperheroesTheme {
                Scaffold(
                    topBar = { HeroTopBar() },
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    HeroApp(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HeroTopBar() {

    CenterAlignedTopAppBar(
        title = {
            Row(verticalAlignment = Alignment.CenterVertically) {

                Text(
                    text = "Superheroes",
                    style = MaterialTheme.typography.displayLarge,
                )

            }
        },
        modifier = Modifier
    )

}

@Composable
fun HeroApp(modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(heroes) { hero ->
            HeroItem(hero)
        }
    }
}

@Composable
private fun HeroItem(
    hero: Hero, // contient toutes les infos des heros, hero c une instance de la classe Hero
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) { // espace entre les cartes
        Row(modifier = Modifier, verticalAlignment = Alignment.CenterVertically) {

            HeroTextes(hero, Modifier.weight(1f))

            Image(
                painter = painterResource(hero.imageRes), // pas centré cest quelle prend toute la place
                contentDescription = null,
                modifier = Modifier
                    .size(120.dp)
                    .padding(24.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )

        }
    }
}

@Composable
fun HeroTextes(hero: Hero, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(
            text = stringResource(hero.nameRes),
            style = MaterialTheme.typography.displayMedium,
            modifier = Modifier
                .padding(
                    top = 8.dp,
                    bottom = 8.dp,
                    end = 8.dp,
                    start = 16.dp
                ),
        )
        Text(
            text = stringResource(hero.descriptionRes),
            modifier = Modifier
                .padding(bottom = 8.dp, end = 8.dp, start = 16.dp),
            style = MaterialTheme.typography.labelSmall,
        )
    }
}

@Preview
@Composable
private fun HeroPreview() {
    HeroItem(
        Hero(
            nameRes = R.string.hero2,
            descriptionRes = R.string.description2,
            imageRes = R.drawable.android_superhero2
        )
    )

}

@Preview(showBackground = true)
@Composable
private fun Rangee() {
    Row() {

        Textes("bonjour", "salut")

        Image(painter = painterResource(R.drawable.android_superhero2), contentDescription = null)
    }
}

@Composable
private fun Textes(motUn: String, motDeux: String) {
    Column() {
        Text(text = motUn)
        Text(text = motDeux)
    }
}

