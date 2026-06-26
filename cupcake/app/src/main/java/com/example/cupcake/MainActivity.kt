package com.example.cupcake

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.example.cupcake.ui.theme.CupcakeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CupcakeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CupcakeApp(
                        modifier = Modifier.padding(innerPadding) // si tu mets du .background(Color.Red) avant le padding tu verras que cxa met de lespace entre le texte et le cupcake
                    )
                }
            }
        }
    }
}

@Composable
fun CupcakeApp(modifier: Modifier) {
    CupcakeDisplay(modifier = modifier) // sinon il apllique pas le innerPadding et dit que le modifier est pas utilisé

}

@Composable
fun CupcakeDisplay(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,

        ) {

        Column(modifier = Modifier.weight(2f)) {

        Image(
            painter = painterResource(R.drawable.cupcake), contentDescription = null

        )

        Text(
            text = "Order Cupcakes",
            modifier = modifier,

            )
    }

        Column(
            modifier = Modifier
                .background(Color.Red)
                .fillMaxSize()
                .weight(1f),
//            verticalArrangement = Arrangement.Bottom,
//            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = {/*todo */ }) {
                Text(text = "One cupcake")
            }
            Button(onClick = {/*todo */ }) {
                Text(text = "Six Cupcakes")
            }

            Button(onClick = {/*todo */ }) {
                Text(text = "Twelve Cupcakes")
            }
        }

    }
}

//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    CupcakeTheme {
//        CupcakeApp()
//    }
//}