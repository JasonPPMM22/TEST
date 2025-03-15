package com.proj.tictactoe
import android.util.Log

class TicTacToeViewModel {
    // Properties to track the game state
    var currentPlayer: TicTacToePlayer = TicTacToePlayer.X
    var gameBoard: Array<Array<TicTacToeCellState>> = Array(3) { Array(3) { TicTacToeCellState.EMPTY } }

    // Function to handle cell clicks
    fun onCellClick(row: Int, col: Int) {
        if (gameState == GameState.InProgress && gameBoard[row][col] == TicTacToeCellState.EMPTY) {
            gameBoard[row][col] = if (currentPlayer == TicTacToePlayer.X) TicTacToeCellState.X else TicTacToeCellState.O
            currentPlayer = if (currentPlayer == TicTacToePlayer.X) TicTacToePlayer.O else TicTacToePlayer.X

            // Add a log statement to track the state changes
            Log.d("TicTacToeViewModel", "Cell clicked at ($row, $col)")
            Log.d("TicTacToeViewModel", "Updated gameBoard: ${
                gameBoard.joinToString("\n") {
                    it.joinToString(
                        " "
                    )
                }
            }")
            Log.d("TicTacToeViewModel", "Current player: $currentPlayer")

            // Check for a win or draw after each move
            checkGameResult()
        }
    }

    // Enum to represent players
    enum class TicTacToePlayer {
        X, O
    }

    // Enum to represent the state of a cell
    enum class TicTacToeCellState {
        EMPTY, X, O
    }

    // Enum to represent the game state
    enum class GameState {
        PlayerXWon,
        PlayerOWon,
        Draw,
        InProgress
    }

    // Property to track the game state
    var gameState: GameState = GameState.InProgress

    // Function to check for a win or draw
    private fun checkGameResult() {
        // Check rows, columns, and diagonals for a win
        for (i in 0 until 3) {
            if (gameBoard[i][0] == gameBoard[i][1] && gameBoard[i][1] == gameBoard[i][2] && gameBoard[i][0] != TicTacToeCellState.EMPTY) {
                gameState = if (gameBoard[i][0] == TicTacToeCellState.X) GameState.PlayerXWon else GameState.PlayerOWon
                return
            }
            if (gameBoard[0][i] == gameBoard[1][i] && gameBoard[1][i] == gameBoard[2][i] && gameBoard[0][i] != TicTacToeCellState.EMPTY) {
                gameState = if (gameBoard[0][i] == TicTacToeCellState.X) GameState.PlayerXWon else GameState.PlayerOWon
                return
            }
        }
        if (gameBoard[0][0] == gameBoard[1][1] && gameBoard[1][1] == gameBoard[2][2] && gameBoard[0][0] != TicTacToeCellState.EMPTY) {
            gameState = if (gameBoard[0][0] == TicTacToeCellState.X) GameState.PlayerXWon else GameState.PlayerOWon
            return
        }
        if (gameBoard[0][2] == gameBoard[1][1] && gameBoard[1][1] == gameBoard[2][0] && gameBoard[0][2] != TicTacToeCellState.EMPTY) {
            gameState = if (gameBoard[0][2] == TicTacToeCellState.X) GameState.PlayerXWon else GameState.PlayerOWon
            return
        }

        // Check for a draw
        if (gameBoard.flatten().none { it == TicTacToeCellState.EMPTY }) {
            gameState = GameState.Draw
        }
    }

    // Function to reset the game
    fun resetGame() {
        currentPlayer = TicTacToePlayer.X
        gameBoard = Array(3) { Array(3) { TicTacToeCellState.EMPTY } }
        gameState = GameState.InProgress

        // Add a log statement to track the state changes when the game is reset
        Log.d("TicTacToeViewModel", "Game reset")
    }
}