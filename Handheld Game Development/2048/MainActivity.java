package edu.angelo.midtermprojecthong;

/**
 * @author Seoyeon Hong
 */

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // create Board
        gameBoard = new Board(NUM_ROWS, NUM_COLUMNS, colors.length);

        // initialize lightButtons with button views
        lightButtons = new Button[][] {{
                findViewById(R.id.button00),
                findViewById(R.id.button01),
                findViewById(R.id.button02),
                findViewById(R.id.button03)
        }, {
                findViewById(R.id.button10),
                findViewById(R.id.button11),
                findViewById(R.id.button12),
                findViewById(R.id.button13)
        }, {
                findViewById(R.id.button20),
                findViewById(R.id.button21),
                findViewById(R.id.button22),
                findViewById(R.id.button23)
        }, {
                findViewById(R.id.button30),
                findViewById(R.id.button31),
                findViewById(R.id.button32),
                findViewById(R.id.button33)
        }};

        // initialize moveButtons with button views
        moveButtons = new Button[] {
                findViewById(R.id.upButton),
                findViewById(R.id.downButton),
                findViewById(R.id.rightButton),
                findViewById(R.id.leftButton)
        };

        // update the game board
        updateGameBoard();
    }

    private final int NUM_ROWS = 4; // number of rows of gameBoard
    private final int NUM_COLUMNS = 4; // number of columns of gameBoard

    // save colors in int array.
    private final int[] colors = new int[] {
            Color.rgb(58, 61, 76), // navy blue
            Color.rgb(52, 85, 139), // ocean blue
            Color.rgb(121, 143, 167), // faded denim
            Color.rgb(160, 156, 155), // ash
            Color.rgb(113, 97, 124),  // grape compote
            Color.rgb(180, 155, 114),  // lark
            Color.rgb(87, 92, 70),  // chive
            Color.rgb(162, 85, 59),  // cinnamon stick
            Color.rgb(254, 176, 18),  // saffron
            Color.rgb(254, 129, 62),  // orange peel
            Color.rgb(209, 58, 65)  // flame scarlet
    };

    private Board gameBoard; // Board object which tracks status of game board.
    private Button[][] lightButtons; // two dimensional array of Buttons
    private Button[] moveButtons; // array of move buttons

    /**
     * update game board so the buttons reflect current values
     */
    private void updateGameBoard(){
        TextView tv = findViewById(R.id.textView);
        TextView st = findViewById(R.id.scoreText);

        int num = 0;
        // update the background color of each Button and set text
        for(int i = 0; i < NUM_ROWS; i++){
            for(int j = 0; j < NUM_COLUMNS; j++){
                num = gameBoard.getNum(i, j);
                if(num != 0){
                    lightButtons[i][j].setBackgroundColor(colors[num]);
                    num = (int)Math.pow(2, num);
                    lightButtons[i][j].setText(Integer.toString(num));
                    lightButtons[i][j].setTextColor(Color.WHITE);
                }
                // set button with default color and no text if the value is 0
                else{
                    lightButtons[i][j].setBackgroundResource(android.R.drawable.btn_default);
                    lightButtons[i][j].setText("");
                }

            }
        }

        // set score text
        st.setText("Score: " + gameBoard.getScore());

        // change the text if game is cleared or over
        if(gameBoard.isCleared){
            tv.setText("WINNER IS YOU");
            tv.setTextColor(Color.WHITE);
            tv.setBackgroundColor(Color.rgb(50,150, 50));

            // set move buttons disabled
            for(int i = 0; i < moveButtons.length; i++){
                moveButtons[i].setEnabled(false);
            }
        }
        else if(gameBoard.isOver){
            tv.setText("Game Over");
            tv.setTextColor(Color.WHITE);
            tv.setBackgroundColor(Color.rgb(150,50, 50));

            // set move buttons disabled
            for(int i = 0; i < moveButtons.length; i++){
                moveButtons[i].setEnabled(false);
            }
        }
        else{
            tv.setText("Join the tiles, get to 2048!");
            tv.setTextColor(Color.GRAY);
            tv.setBackgroundColor(Color.WHITE);
        }
    }

    /**
     * call move method of Board when buttons are clicked
     * @param v button view which is clicked
     */
    public void move(View v){
        Button b = (Button)v;
        String str = b.getText().toString();

        // call move method
        switch(str){
            case "Up":
                gameBoard.moveUp();
                break;
            case "Down":
                gameBoard.moveDown();
                break;
            case "Left":
                gameBoard.moveLeft();
                break;
            case "Right":
                gameBoard.moveRight();
                break;
            default:
                break;
        }

        // update game board
        updateGameBoard();

        // if any element is changed, make new number and set isChanged false
        if(gameBoard.isChanged){
            gameBoard.makeNewNum();
            gameBoard.isChanged = false;
        }
        // if the board is full and can't move anything, set isOver true
        else{
            if(gameBoard.isfull() & !gameBoard.isMoveable()){
                gameBoard.isOver = true;
            }
        }

        // update game board
        updateGameBoard();

    }

    /**
     * call randomize method of Board when restart button is clicked
     * @param v button view which is clicked
     */
    public void startNewGame(View v){
        gameBoard.randomize();

        // set move buttons enabled
        for(int i = 0; i < moveButtons.length; i++){
            moveButtons[i].setEnabled(true);
        }
        updateGameBoard();
    }
}
