package dcdesign.tictactoe;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Board gameBoard;
    ComputerPlayer computer;

    String playerOneName = "Daniel";
    String playerTwoName = "Emily";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //create gameboard
        gameBoard = new Board(playerOneName, playerTwoName);

        //create computerPlayer
        computer = new ComputerPlayer(gameBoard);

        //setup button click handlers
        setupButtons();

        //update game
        updateGame();
    }

    private void startGame() {
        Log.i("Game Status", "Game has started");

        gameBoard.resetBoard();

        playerOneName = ((EditText) findViewById(R.id.txtPlayerOneName)).getText().toString();
        playerTwoName = ((EditText) findViewById(R.id.txtPlayerTwoName)).getText().toString();

        gameBoard._playerOneName = playerOneName;
        gameBoard._playerTwoName = playerTwoName;
        computer._name = playerTwoName;

        gameBoard._activePlayerName = playerOneName;

        gameBoard._gameStarted = true;

        updateGame();
    }
    private void updateGame() {
        //show whos turn it is
        if (!gameBoard._gameOver && gameBoard._gameStarted)
            ((TextView) findViewById(R.id.lblActivePlayer)).setText(String.valueOf("It is " + gameBoard._activePlayerName + "'s turn"));
        else
            ((TextView) findViewById(R.id.lblActivePlayer)).setText(String.valueOf(""));

        //check for a winner
        if (gameBoard.isGameWon())
        {
            ((TextView) findViewById(R.id.lblActivePlayer)).setText(String.valueOf(gameBoard._winnerName + " has won!"));
        }

        //handle computer input if applicable
        if (((CheckBox) findViewById(R.id.chkComputer)).isChecked() && gameBoard._activePlayerName == computer._name)
        {
            computer.makeNextMove();
        }

        //show whos turn it is after computer's move
        if (!gameBoard._gameOver && gameBoard._gameStarted)
            ((TextView) findViewById(R.id.lblActivePlayer)).setText(String.valueOf("It is " + gameBoard._activePlayerName + "'s turn"));
        else
            ((TextView) findViewById(R.id.lblActivePlayer)).setText(String.valueOf(""));

        //check for a winner
        if (gameBoard.isGameWon())
        {
            ((TextView) findViewById(R.id.lblActivePlayer)).setText(String.valueOf(gameBoard._winnerName + " has won!"));
        }

        //check for a tie
        if(gameBoard.isGameTied())
        {
            ((TextView) findViewById(R.id.lblActivePlayer)).setText(String.valueOf("Game is a Tie!"));
        }

        //refresh values of boxes
        refreshBoard();
    }

    private void refreshBoard() {
        //refresh game board
        ((TextView) findViewById(R.id.btnTopLeft)).setText((String.valueOf(gameBoard._spaces[0][0].currentValue)));
        ((TextView) findViewById(R.id.btnTopMiddle)).setText((String.valueOf(gameBoard._spaces[0][1].currentValue)));
        ((TextView) findViewById(R.id.btnTopRight)).setText((String.valueOf(gameBoard._spaces[0][2].currentValue)));
        ((TextView) findViewById(R.id.btnMiddleLeft)).setText((String.valueOf(gameBoard._spaces[1][0].currentValue)));
        ((TextView) findViewById(R.id.btnMiddleMiddle)).setText((String.valueOf(gameBoard._spaces[1][1].currentValue)));
        ((TextView) findViewById(R.id.btnMiddleRight)).setText((String.valueOf(gameBoard._spaces[1][2].currentValue)));
        ((TextView) findViewById(R.id.btnBottomLeft)).setText((String.valueOf(gameBoard._spaces[2][0].currentValue)));
        ((TextView) findViewById(R.id.btnBottomMiddle)).setText((String.valueOf(gameBoard._spaces[2][1].currentValue)));
        ((TextView) findViewById(R.id.btnBottomRight)).setText((String.valueOf(gameBoard._spaces[2][2].currentValue)));
    }

    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.btnStart:
                Log.d("Button Start", "Start Button Clicked");
                startGame();
                updateGame();
                break;
            case R.id.btnTopLeft:
                Log.d("Button TopLeft", "TopLeft Button Clicked");
                gameBoard.handleClick(0,0);
                updateGame();
                break;
            case R.id.btnTopMiddle:
                Log.d("Button TopMiddle", "TopMiddle Button Clicked");
                gameBoard.handleClick(0,1);
                updateGame();
                break;
            case R.id.btnTopRight:
                Log.d("Button TopRight", "TopRight Button Clicked");
                gameBoard.handleClick(0,2);
                updateGame();
                break;
            case R.id.btnMiddleLeft:
                Log.d("Button MiddleLeft", "MiddleLeft Button Clicked");
                gameBoard.handleClick(1,0);
                updateGame();
                break;
            case R.id.btnMiddleMiddle:
                Log.d("Button MiddleMiddle", "MiddleMiddle Button Clicked");
                gameBoard.handleClick(1,1);
                updateGame();
                break;
            case R.id.btnMiddleRight:
                Log.d("Button MiddleRight", "MiddleRight Button Clicked");
                gameBoard.handleClick(1,2);
                updateGame();
                break;
            case R.id.btnBottomLeft:
                Log.d("Button BottomLeft", "BottomLeft Button Clicked");
                gameBoard.handleClick(2,0);
                updateGame();
                break;
            case R.id.btnBottomMiddle:
                Log.d("Button BottomMiddle", "BottomMiddle Button Clicked");
                gameBoard.handleClick(2,1);
                updateGame();
                break;
            case R.id.btnBottomRight:
                Log.d("Button BottomRight", "BottomRight Button Clicked");
                gameBoard.handleClick(2,2);
                updateGame();
                break;
            default:
                break;
        }

    }

    private void setupButtons() {
        Button btnStart = (Button) findViewById(R.id.btnStart);
        btnStart.setOnClickListener(this);

        Button btnTopLeft = ((Button) findViewById(R.id.btnTopLeft));
        btnTopLeft.setOnClickListener(this);

        Button btnTopMiddle = (Button) findViewById(R.id.btnTopMiddle);
        btnTopMiddle.setOnClickListener(this);

        Button btnTopRight = (Button) findViewById(R.id.btnTopRight);
        btnTopRight.setOnClickListener(this);

        Button btnMiddleLeft = (Button) findViewById(R.id.btnMiddleLeft);
        btnMiddleLeft.setOnClickListener(this);

        Button btnMiddleMiddle = (Button) findViewById(R.id.btnMiddleMiddle);
        btnMiddleMiddle.setOnClickListener(this);

        Button btnMiddleRight = (Button) findViewById(R.id.btnMiddleRight);
        btnMiddleRight.setOnClickListener(this);

        Button btnBottomLeft = (Button) findViewById(R.id.btnBottomLeft);
        btnBottomLeft.setOnClickListener(this);

        Button btnBottomMiddle = (Button) findViewById(R.id.btnBottomMiddle);
        btnBottomMiddle.setOnClickListener(this);

        Button btnBottomRight = (Button) findViewById(R.id.btnBottomRight);
        btnBottomRight.setOnClickListener(this);
    }
}
