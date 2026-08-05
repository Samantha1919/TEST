package com.example.race.ui

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.delay

class RaceParticipant(
    val name: String,
    val maxProgress: Int = 100,
    val progressDelayMillis: Long = 500L, // delais entre chaque increment
    private val progressIncrement: Int = 1, // ca monte de 1 a chaque fois
    private val initialProgress: Int = 0
) {
    init {
        require(maxProgress > 0) { "maxProgress=$maxProgress; must be > 0" }
        require(progressIncrement > 0) { "progressIncrement=$progressIncrement; must be > 0" }
    }

    var currentProgress by mutableStateOf(initialProgress) // prend initialProgress en valeur de depart
        private set

    suspend fun run() {
        try {
            while (currentProgress < maxProgress) {
                delay(progressDelayMillis) // essayer de le commenter apres pr voir ce que ca fait
                currentProgress =
                    currentProgress + progressIncrement  // en gros ca rajoute 1 a cahque fois a la variable currentProgress qui est egale a 0 au debut ducoup au 1er tour, currentProgress += progressIncrement je lai ecris détaillé psq je trouve ca + simple
            }
        } catch (e: CancellationException) {
            Log.e(
                "RaceParticipant",
                "$name: ${e.message}"
            ) // Player 1 ou 2: The coroutine scope left the composition
            throw e
        }
    }

    fun reset() {
        currentProgress = 0
    }
}

val RaceParticipant.progressFactor: Float
    get() = currentProgress / maxProgress.toFloat()