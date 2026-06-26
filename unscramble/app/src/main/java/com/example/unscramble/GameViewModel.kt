package com.example.unscramble

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.unscramble.data.SCORE_INCREASE
import com.example.unscramble.data.allWords
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class GameViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(GameUiState())//maj letat et lenv au flux

    val uiState: StateFlow<GameUiState> =
        _uiState.asStateFlow() // propriété de support-> accessible et modifiable uniquement dans GameViewModel (??) /asStateFlow transforme le flux detat modifiable en en f.de en lecture seule
    private lateinit var currentWord: String // mot mtn/courant

    var userGuess by mutableStateOf("")
        private set // tout le monde peut lire userGuess, mais seule cette classe peut le modifier

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

    fun updateUserGuess(guessedWord: String) { // userGuess le mot deviné par luser, écrit et il va dans la variable guessedWord/  // met à jour userGuess avec le nouveau texte saisi et userGuess est la variable dans le ViewModel/// guessedWord est le texte que l'utilisateur vient de saisir
        userGuess =
            guessedWord         // On met à jour la variable userGuess avec cette nouvelle valeur

    }

    fun checkUserGuess() {

        if (userGuess.equals(currentWord, ignoreCase = true)) {
            val updatedScore = _uiState.value.score.plus(SCORE_INCREASE)
            updateGameState(updatedScore) // remplacer un chiffre dedans pr voir () ?
        } else {
            // User's guess is wrong, show an error
            _uiState.update { currentState ->
                currentState.copy(isGuessedWordWrong = true)
            }
            // reinittialise/change la valeur/le mot dans variable user guess avec cette fonction
            updateUserGuess("")
        }
    }

    private fun updateGameState(updatedScore: Int) { // met a jour le score, augmente le nb de mots choisi, et choisiti un nv mot dans le fichhier
        _uiState.update { currentState ->
            currentState.copy(
                isGuessedWordWrong = false,
                currentScrambledWord = pickRandomWordAndShuffle(),
                score = updatedScore,
                currentWordCount = currentState.currentWordCount.inc()
            )
        }
    }

    fun skipWord() {
        updateGameState(_uiState.value.score)
        // Reset user guess
        updateUserGuess("")
    }

    init {
        resetGame()
    }
}
