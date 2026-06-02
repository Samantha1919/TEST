package com.example.diceroller

import android.R.attr.text
import android.os.Bundle
import android.util.Log
import android.util.Log.i
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.mutableStateSetOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.diceroller.ui.theme.DiceRollerTheme
import javax.security.auth.login.LoginException

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DiceRollerTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    DiceApp(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun DiceApp(modifier: Modifier = Modifier) {
    DiceWithButton(modifier = modifier.fillMaxSize())
}

@Composable
fun DiceWithButton(modifier: Modifier = Modifier) {
    var result by remember { mutableStateOf(1) } // psq il change
    val NumDe = when (result) {
        1 -> R.drawable.dice_1
        2 -> R.drawable.dice_2
        3 -> R.drawable.dice_3 // de type Int
        4 -> R.drawable.dice_4
        5 -> R.drawable.dice_5
        else -> R.drawable.dice_6 // le when fait tt les cas de figures et ca peut etre nimporte quel nombre pas que jusqua 6 ca peut etre + grand dcp on met else pr gerer tt les cas de figueres, le when prend tt en cas de figure mais pas les enum
    }

    val painter =
        painterResource(NumDe) // painterResource(NumDe) est de type Painter, il va transofrmer les images de NumDe en type Painter (elles etaient de type Int a la base)

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Image(
            painter = painter,
            contentDescription = null
        ) // Image(painter = painterResource(NumDe), contentDescription = null)
        Button(
            onClick =
                {
                    result = (1..6).random() // fait une liste de 1,2,3,4,5,6
                    Log.e("test", "DiceWithButton: $result")
                }) {
            Text(
                text = "Roll"
            )
        }
    }
}

