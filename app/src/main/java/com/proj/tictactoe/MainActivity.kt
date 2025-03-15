package com.proj.tictactoe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.proj.tictactoe.ui.theme.TicTacToeTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Create an instance of your ViewModel
        val viewModel = TicTacToeViewModel()

        setContent {
            TicTacToeTheme {
                TicTacToeScreen(viewModel) // Pass the viewModel to TicTacToeScreen
            }
        }
    }


    @Composable
    @Preview
    fun MainScreenPreview() {
        val viewModel = TicTacToeViewModel() // Create an instance of TicTacToeViewModel
        TicTacToeTheme {
            TicTacToeScreen(viewModel)
        }
    }
}