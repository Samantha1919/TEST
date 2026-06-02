package com.example.lemonade

import android.R.attr.name
import android.R.attr.text
import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun TreeScreen(modifier: Modifier) {
    val name = "nom" // la on lui assigne sa valeur
    val nameComplet = name + " prénom"
    Text(text = nameComplet, modifier = modifier) // la on affiche la valeur de name

    Log.d("test", "TreeScreen: $name")
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    TreeScreen(modifier = Modifier)
}