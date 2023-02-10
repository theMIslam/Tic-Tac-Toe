package com.example.krestikinoliki

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    class TicTacToe {
        private val board = Array(3) { CharArray(3) { ' ' } }
        private var player = 'X'

        private fun printBoard() {
            println("\n")
            for (i in 0..2) {
                print(" " + board[i][0] + " | " + board[i][1] + " | " + board[i][2])
                if (i != 2) {
                    println("\n---|---|---")
                }
            }
            println("\n")
        }

        private fun changePlayer() {
            player = if (player == 'X') 'O' else 'X'
        }

        private fun getPlayerMove(): Pair<Int, Int> {
            print("Player $player, enter your move (row column): ")
            val move = readLine()!!.split(" ").map { it.toInt() }
            return Pair(move[0] - 1, move[1] - 1)
        }

        private fun makeMove(move: Pair<Int, Int>) {
            if (board[move.first][move.second] == ' ') {
                board[move.first][move.second] = player
            } else {
                println("Position already occupied, enter a valid move")
            }
        }

        private fun checkWinningCondition(): Boolean {
            // Check rows
            for (i in 0..2) {
                if (board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][0] != ' ') {
                    return true
                }
            }

            // Check columns
            for (i in 0..2) {
                if (board[0][i] == board[1][i] && board[1][i] == board[2][i] && board[0][i] != ' ') {
                    return true
                }
            }

            // Check diagonals
            if (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != ' ' ||
                board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[0][2] != ' '
            ) {
                return true
            }

            return false
        }

        fun play() {
            printBoard()
            while (true) {
                val move = getPlayerMove()
                makeMove(move)
                printBoard()
                if (checkWinningCondition()) {
                    println("Player $player wins!")
                    break
                }
                if (board.flatMap { it.asList() }.none { it == ' ' }) {
                    println("It's a draw.")
                    break
                }
                changePlayer()
            }
        }
    }

    fun main() {
        val game = TicTacToe()
        game.play()
    }
}
