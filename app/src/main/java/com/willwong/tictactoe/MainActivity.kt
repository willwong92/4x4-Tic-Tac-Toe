package com.willwong.tictactoe

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayout
import android.view.View
import android.widget.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private val WIN_FLAG = 1

    private var gridArray = IntArray(16){i -> 0}
    private lateinit var cellImageZero: ImageView
    private lateinit var cellImageOne: ImageView
    private lateinit var cellImageTwo: ImageView
    private lateinit var cellImageThree: ImageView
    private lateinit var cellImageFour: ImageView
    private lateinit var cellImageFive: ImageView
    private lateinit var cellImageSix: ImageView
    private lateinit var cellImageSeven: ImageView
    private lateinit var cellImageEight: ImageView
    private lateinit var cellImageNine: ImageView
    private lateinit var cellImageTen: ImageView
    private lateinit var cellImageEleven: ImageView
    private lateinit var cellImageTwelve: ImageView
    private lateinit var cellImageThirteen: ImageView
    private lateinit var cellImageFourteen: ImageView
    private lateinit var cellImageFifteen: ImageView

    private lateinit var currentPlayer : ImageView

    private lateinit var boardLayout: android.support.v7.widget.GridLayout

    private lateinit var playerStatus: TextView

    private lateinit var newGame: Button

    private var playerRole: Boolean = true

    private lateinit var player: Player


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        playerStatus = findViewById(R.id.player_turn)

        cellImageZero = findViewById(R.id.cell_0)
        cellImageOne = findViewById(R.id.cell_1)
        cellImageTwo = findViewById(R.id.cell_2)
        cellImageThree = findViewById(R.id.cell_3)
        cellImageFour = findViewById(R.id.cell_4)
        cellImageFive = findViewById(R.id.cell_5)
        cellImageSix = findViewById(R.id.cell_6)
        cellImageSeven = findViewById(R.id.cell_7)
        cellImageEight = findViewById(R.id.cell_8)
        cellImageNine = findViewById(R.id.cell_9)
        cellImageTen = findViewById(R.id.cell_10)
        cellImageEleven = findViewById(R.id.cell_11)
        cellImageTwelve = findViewById(R.id.cell_12)
        cellImageThirteen = findViewById(R.id.cell_13)
        cellImageFourteen = findViewById(R.id.cell_14)
        cellImageFifteen = findViewById(R.id.cell_15)

        currentPlayer = findViewById(R.id.display_image)

        boardLayout = findViewById(R.id.board_grid)

        newGame = findViewById(R.id.new_game_button)

        initGame()
        cellImageZero.setOnClickListener(this)
        cellImageOne.setOnClickListener(this)
        cellImageTwo.setOnClickListener(this)
        cellImageThree.setOnClickListener(this)
        cellImageFour.setOnClickListener(this)
        cellImageFive.setOnClickListener(this)
        cellImageSix.setOnClickListener(this)
        cellImageSeven.setOnClickListener(this)
        cellImageEight.setOnClickListener(this)
        cellImageNine.setOnClickListener(this)
        cellImageTen.setOnClickListener(this)
        cellImageEleven.setOnClickListener(this)
        cellImageTwelve.setOnClickListener(this)
        cellImageThirteen.setOnClickListener(this)
        cellImageFourteen.setOnClickListener(this)
        cellImageFifteen.setOnClickListener(this)

        newGame.setOnClickListener(this)


        currentPlayer.setImageResource(R.drawable.cross)
        playerStatus.setText(getString(R.string.current_player))

    }

    fun initGame() {
        player = Player(playerRole)
    }

    override fun onClick(v: View?) {

        if (v is ImageView) {
            cellClick(v)
        } else if (v!!.id == R.id.new_game_button) {
            newGame()
        }
    }

    fun cellClick(v: View) {
        var cellImage: ImageView = v as ImageView
        var cell = Integer.parseInt(cellImage.getTag().toString())
        if (player.getCurrentPlayer()) {
            cellOccupy(cell)
            gridArray[cell] = 1
            cellImage.setImageResource(R.drawable.cross)
            cellImage.setEnabled(false)
            if (player.checkForWin() == WIN_FLAG) {
                disableButtons(boardLayout)
                Toast.makeText(this, player.getPlayer() + " has won!", Toast.LENGTH_LONG).show()
            } else {
                checkForTie()
            }
            player.switchPlayer()
            playerStatus.setText(resources.getString(R.string.current_player))
            currentPlayer.setImageResource(R.drawable.circle)
            //player.switchPlayer()
        } else {
            cellOccupy(cell)
            gridArray[cell] = 1
            cellImage.setImageResource(R.drawable.circle)
            cellImage.setEnabled(false)

            if (player.checkForWin() == WIN_FLAG) {
                disableButtons(boardLayout)
                Toast.makeText(this, player.getPlayer() + " has won!", Toast.LENGTH_LONG).show()

            } else {
                checkForTie()
            }
            player.switchPlayer()
            playerStatus.setText(resources.getString(R.string.current_player))
            currentPlayer.setImageResource(R.drawable.cross)
        }
    }

    fun checkForTie() {
        var sum = 0
        for (i in 0 until gridArray.size) {
            sum += gridArray[i]
        }
        if (sum == 16) {
            disableButtons(boardLayout)
            Toast.makeText(this, "Game is a tie!", Toast.LENGTH_LONG).show()
        }
    }

    fun cellOccupy(cell : Int) {
        when (cell) {
            0 -> player.setMoves(0,0)
            1 -> player.setMoves(0,1)
            2 -> player.setMoves(0,2)
            3 -> player.setMoves(0,3)
            4 -> player.setMoves(1,0)
            5 -> player.setMoves(1,1)
            6 -> player.setMoves(1,2)
            7 -> player.setMoves(1,3)
            8 -> player.setMoves(2,0)
            9 -> player.setMoves(2,1)
            10 -> player.setMoves(2,2)
            11 -> player.setMoves(2,3)
            12 -> player.setMoves(3,0)
            13 -> player.setMoves(3,1)
            14 -> player.setMoves(3,2)
            15 -> player.setMoves(3,3)

        }
    }
    fun newGame() {
        playerRole = true
        player = Player(playerRole)
        for (i in 0 until boardLayout.childCount) {
            val child = boardLayout.getChildAt(i) as ImageView
            child.setImageResource(0)
            child.setEnabled(true)
            currentPlayer.setImageResource(R.drawable.cross)
        }
    }

    fun disableButtons(layout : GridLayout) {
        for (i in 0 until layout.childCount) {
            val child = layout.getChildAt(i) as ImageView
            child.setEnabled(false)
        }
        gridArray = IntArray(16){i -> 0}

    }
}

