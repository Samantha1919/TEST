package com.example.cupcake

import androidx.navigation.NavController
import org.junit.Assert.assertEquals

fun NavController.assertCurrentRouteName(expectedRouteName: String) { // expectedRouteName est égal à l'itinéraire de destination de l'entrée de la pile "Retour" du contrôleur de navigation.
    assertEquals(expectedRouteName, currentBackStackEntry?.destination?.route)
}