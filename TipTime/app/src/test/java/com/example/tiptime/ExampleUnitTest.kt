package com.example.tiptime

import org.junit.Assert.assertEquals
import org.junit.Test
import java.text.NumberFormat

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

//

class ExampleUnitTest { // start le test ici
    @Test
    fun calculateTip_20PercentNoRoundup() {
        val montantFacture = 10.00
        val pourboirePourcent = 20.0
        val pourboireAttendu = NumberFormat.getCurrencyInstance().format(2)
        val pourboireActuel = calculateTip(
            montantFacture = montantFacture,
            pourboirePourcent = pourboirePourcent,
            roundUp = false
        )
        assertEquals( // assertion -> verifie si les 2 valeurs sont egales
            pourboireAttendu,
            pourboireActuel
        )
    }
}