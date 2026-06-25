package com.example.unscramble

import androidx.lifecycle.ViewModel
import com.example.unscramble.data.allWords
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class GameViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(GameUiState())//maj letat et lenv au flux

    val uiState: StateFlow<GameUiState> = _uiState.asStateFlow() // propriété de support-> accessible et modifiable uniquement dans GameViewModel (??) /asStateFlow transforme le flux detat modifiable en en f.de en lecture seule
    private lateinit var currentWord: String // mot mtn/courant

    private var usedWords: MutableSet<String> = mutableSetOf() // les mots déjà utilisés

    // melanger le mot actuel
    private fun shuffleCurrentWord(word: String): String { // lit et renvoie le mot actuel en mode aleatoire genre tt melangé ?
        val tempWord = word.toCharArray() // mot temporaire
        // melange le mot
        tempWord.shuffle()
        while (String(tempWord).equals(word)) {
            tempWord.shuffle()
        }
        return String(tempWord)
    }

    private fun pickRandomWordAndShuffle(): String {
        // Continue à choisir un nouveau mot au hasard jusqu'à en obtenir un qui n'a pas encore été utilisé.
        currentWord = allWords.random()
        if (usedWords.contains(currentWord)) {
            return pickRandomWordAndShuffle()
        } else {
            usedWords.add(currentWord)
            return shuffleCurrentWord(currentWord)
        }
    }

    fun resetGame() {
        usedWords.clear()
        _uiState.value = GameUiState(currentScrambledWord = pickRandomWordAndShuffle())
    }

    init {
        resetGame()
    }
}