package com.proj.tictactoe
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun TicTacToeScreen(viewModel: TicTacToeViewModel) {
    val rememberedViewModel = remember { viewModel }

    Surface(
        modifier = Modifier.fillMaxSize(),
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                TicTacToeGameBoard(rememberedViewModel)
                GameStatus(rememberedViewModel)
                ResetButton(rememberedViewModel)

            }
        }
    }
}

@Composable
fun TicTacToeGameBoard(viewModel: TicTacToeViewModel) {
    for (row in 0 until 3) {
        Row {
            for (col in 0 until 3) {
                val cellState = viewModel.gameBoard[row][col]
                TicTacToeCell(cellState, row, col, viewModel) // This calls the TicTacToeCell composable
            }
        }
    }
}

@Composable
fun TicTacToeCell(cellState: TicTacToeViewModel.TicTacToeCellState, row: Int, col: Int, viewModel: TicTacToeViewModel) {
    Button(
        onClick = { viewModel.onCellClick(row, col) }
    ) {
        val buttonText = when (cellState) {
          TicTacToeViewModel.TicTacToeCellState.X -> "X"
            TicTacToeViewModel.TicTacToeCellState.O -> "O"
            else -> ""
        }

        Text(text = buttonText, color = Color.Black)
    }
}
@Composable
fun GameStatus(viewModel: TicTacToeViewModel) {
    // Display game status based on the ViewModel's state
    val statusText = when (viewModel.gameState) {
        TicTacToeViewModel.GameState.PlayerXWon -> "Player X wins!"
        TicTacToeViewModel.GameState.PlayerOWon -> "Player O wins!"
        TicTacToeViewModel.GameState.Draw -> "It's a draw!"
        else -> "Player ${viewModel.currentPlayer}'s turn"
    }

    // Debug statement
    Text(text = statusText)
}

@Composable
fun ResetButton(viewModel: TicTacToeViewModel) {
    // Button to reset the game
    Button(
        onClick = {
            viewModel.resetGame()
        }
    ) {
        Text("Reset Game")
    }
}