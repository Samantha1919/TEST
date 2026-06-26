package com.example.unscramble.ui

data class GameUiState(
    val isGuessedWordWrong: Boolean = false,
    val currentScrambledWord: String = "",
    val score: Int = 0, // au debut le score est égal a 0
    val currentWordCount: Int = 1, // nb de mots joués
    val isGameOver: Boolean = false
)