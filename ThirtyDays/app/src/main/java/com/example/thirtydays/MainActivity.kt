package com.example.thirtydays

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.compose.ThirtyDaysTheme
import com.example.thirtydays.data.Day
import com.example.thirtydays.data.days

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ThirtyDaysTheme(darkTheme = false) {
                Scaffold(
                    topBar = { DayTopBar() },
                    modifier = Modifier.fillMaxSize() // la topBar ne prend pas le innerPadding
                ) { innerPadding ->
                    DayApp(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DayTopBar() {
    CenterAlignedTopAppBar( // pas bs dalignement c deja centré
        title = {
            Row() {
                Text(text = "(Not really) 30 days")
            }
        }
    )
}

@Composable
fun DayApp(modifier: Modifier = Modifier) {
    LazyColumn(
        modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) { // cest ca qui prend le innerPadding donc m minuscule, seulement dans DayApp psq c la bas quil y est, dans le gros composant
        items(days) { day ->
            DayItem(dayParam = day)
        }
    }
}

// ce qui est dans day ->
@Composable
fun DayItem(dayParam: Day, modifier: Modifier = Modifier) {

    var clique by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically, // IMPORTANTNNTNTN
            horizontalArrangement = Arrangement.SpaceBetween
        )
        {
            Column(modifier = Modifier.padding(8.dp).weight(1f)) {
                Text(
                    text = stringResource(dayParam.name),
                    style = MaterialTheme.typography.bodyLarge
                )

                if (clique) { // si clique est a true
                    DayDescritpion(dayParam.description) // la ou on met la description avec dayParam qui existe ici

                }

            }

            Image(
                painter = painterResource(dayParam.imageRessource),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(120.dp)
                    .clickable { clique = !clique }
            )

        }
    }

}

@Composable
fun DayDescritpion(description: Int) { // psq description est de type Int

    Text(text = stringResource(description))

}
