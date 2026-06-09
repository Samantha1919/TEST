package com.example.tiptime

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.tiptime.ui.theme.TipTimeTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.text.NumberFormat

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

// Les tests d'instrumentation testent une instance réelle de l'application et de son interface utilisateur

@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun calculate_20_percent_tip() { // start le test ici
        composeTestRule.setContent {
            TipTimeTheme {
                PlanTexte()
            }
        }
        composeTestRule.onNodeWithText("Bill Amount") // accede au composable TextField
            .performTextInput("10") // on verifie si cest le nombre 10 qui est ecrit dans Bill Amount

        composeTestRule.onNodeWithText("Tip Percentage")
            .performTextInput("20") // verifie si le nombre ecrit dans Tip Percentage est 20
        val expectedTip = NumberFormat.getCurrencyInstance().format(2)
        composeTestRule.onNodeWithText("Tip Amount $expectedTip").assertExists(
            "No node with this text was found." // mess quon renvoie si ca existe pas
        )
    }

}