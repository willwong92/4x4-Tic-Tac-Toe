package com.willwong.tictactoe

class Player(private var player : Boolean ) {

    private val ROWS = 4
    private val COLS = 4
    //Initialize the board to be empty
    private val movesTaken = Array(ROWS, {IntArray(COLS)})



    private var score = 0
    fun switchPlayer() {
        if (player) {
            player = false
        } else {
            player = true
        }
    }

    fun getPlayer() : String {
        if (player) {
            return "X"
        } else {
            return "0"
        }
    }

    fun getCurrentPlayer() : Boolean {
        return player
    }

    fun setMoves(rowPos : Int, colPos: Int) {
        if (player) {
            movesTaken[rowPos][colPos] = -1
        } else {
            movesTaken[rowPos][colPos] = 1
        }


    }
    fun checkForWin() : Int{
        if (checkDiagonal() || checkCorners() || checkHorizontal() || checkSquares() || checkVertical()){
            return 1;
        }
        return 0;
    }
    fun incrementScore() {
        score++
    }
    fun getScore() : Int {
        return score
    }
    fun checkDiagonal() : Boolean {
        var sum = 0
        for (rows in movesTaken.indices){
            sum += movesTaken[rows][rows]
        }
        if (sum == -movesTaken.size || sum == movesTaken.size) {
            return true
        }
        sum = 0
        var end = movesTaken.size-1
        for (i in movesTaken.indices)
        {
            sum += movesTaken[i][end]
            end--
        }
        if (sum == -movesTaken.size || sum == movesTaken.size) {
            return true;
        }
        return false
    }

    fun checkHorizontal() : Boolean {
        var sum = 0
        for (i in movesTaken.indices) {
            sum = 0
            for (j in 0 until movesTaken[i].size) {
                sum += movesTaken[i][j]
            }
            if (sum == -movesTaken.size || sum == movesTaken.size) {
                return true;
            }
        }

        return false;
    }
    fun checkVertical() : Boolean {

        var sum = 0
        for (i in movesTaken.indices) {
            sum = 0
            for (j in 0 until movesTaken[i].size){
                sum += movesTaken[j][i]
            }
            if (sum == -movesTaken.size || sum == movesTaken.size) {
                return true;
            }
        }
        return false;

    }
    fun checkCorners() : Boolean {
        if ((movesTaken[0][0] == -1 && movesTaken[0][COLS-1] == -1
            && movesTaken[ROWS-1][0] == -1 && movesTaken[ROWS-1][COLS-1] == -1) ||
                (movesTaken[0][0] == 1 && movesTaken[0][COLS-1] == 1
                        && movesTaken[ROWS-1][0] == 1 && movesTaken[ROWS-1][COLS-1] == 1)) {
            return true
        }

        return false
    }

    fun checkSquares() : Boolean {
        for (row in movesTaken.indices)
        {
            for (col in 0 until movesTaken[row].size)
            {
                if (movesTaken[row][col] == -1)
                {
                    if (row + 1 < 0 || row + 1 >= movesTaken.size || col + 1 < 0 || col + 1 >= movesTaken.size)
                    {
                        continue
                    }
                    else if (movesTaken[row][col + 1] == -1 && movesTaken[row + 1][col] == -1 && movesTaken[row + 1][col + 1] == -1)
                    {
                        return true
                    }
                }
                if (movesTaken[row][col] == 1)
                {
                    if (row + 1 < 0 || row + 1 >= movesTaken.size || col + 1 < 0 || col + 1 >= movesTaken.size)
                    {
                        continue
                    }
                    else if (movesTaken[row][col + 1] == 1 && movesTaken[row + 1][col] == 1 && movesTaken[row + 1][col + 1] == 1)
                    {
                        return true
                    }
                }
            }
        }
        return false
    }
}