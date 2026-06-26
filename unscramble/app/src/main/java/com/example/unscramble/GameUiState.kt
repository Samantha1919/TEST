package com.example.unscramble

data class GameUiState(
    val isGuessedWordWrong: Boolean = false,
    val currentScrambledWord: String = "",
    val score: Int = 0,
    val currentWordCount: Int = 1, // nb de mots jouée
)


