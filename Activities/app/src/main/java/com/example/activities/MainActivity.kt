package com.example.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.activities.data.DataSource.topics
import com.example.activities.model.Topic
import com.example.activities.ui.theme.ActivitiesTheme
import androidx.compose.material3.CardDefaults

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ActivitiesTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    LazyVerticalGrid(
                        modifier = Modifier
                            .padding(innerPadding).background(Color(0xFFEFE5FF)),
                        columns = GridCells.Fixed(2)
                    ) {
                        items(topics) { topic -> // remplace le foreach je voulais le copier mais g plus le code et on le met ici pour que ca affiche une infoCarte pr chaque topic
                            InfosCarte(topic)
                        }
                    }

                }

            }
        }
    }

    @Composable
    fun InfosCarte(topic: Topic) {
        Card(
            modifier = Modifier
                .padding(8.dp),

                    colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFCDFFD9)
                    )

        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Image(
                    painter = painterResource(topic.image),
                    contentDescription = null
                )
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {

                    Text(
                        text = stringResource(topic.name), // psq dcp c dans strings.xml et apres topic au singulier et on prend le name psq la valeur est de type Int pr le name sinon
                        modifier = Modifier,
                    )

                    Text(text = topic.availableCourses.toString(), modifier = Modifier)
                }
            }
        }

    }

}