package edu.angelo.midtermprojecthong;

/**
 * @author Seoyeon Hong
 */

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Board {
    private final int numRows; // number of rows of board value array
    private final int numColumns; // number of columns of board value array
    private final int numValues; // number of values that board can have
    private static final int maxNum = 2048; // max number


    private int[][] values; // contains the values of each element in board

    private static final Random random = new Random(); // random number to make knew number

    private int score;  // score of the game

    public boolean isCleared = false; // true if the game is cleared
    public boolean isOver = false; // true if the game is over
    public boolean isChanged = false; // true if any element in board is changed

    /**
     * constructor of Board class, create two random numbers with Board.
     * @param numRows number of rows of board
     * @param numColumns number of columns of board
     * @param numValues number of values
     */
    public Board(int numRows, int numColumns, int numValues){
        this.numRows = numRows;
        this.numColumns = numColumns;
        this.numValues = numValues;
        values = new int[numRows][numColumns];
        score = 0;

        // create two numbers.
        makeNewNum();
        makeNewNum();
    }

    /**
     * create new number(2 or 4)
     */
    public void makeNewNum(){
        // create number 1 or 2
        int newNum = random.nextInt(2) + 1;

        // if board is full, return
        if(isfull()){
            return;
        }

        // if board isn't full, add random number in random place
        while(true){
            int row = random.nextInt(4);
            int column = random.nextInt(4);
            if(values[row][column] == 0){
                values[row][column] = newNum;
                return;
            }
        }
    }

    /**
     * return score of the game
     * @return score
     */
    public String getScore(){
        return Integer.toString(score);
    }


    /**
     * move elements to up
     * move all elements upward and join following same numbers and move all elements upward again
     */
    public void moveUp(){
        int [] oldArr = new int[4]; // used to compare old column values and new column values
        int index = 0; // points comparing array index

        // move for each column
        for(int c = 0; c < numColumns; c++) {
            index = 0; // when it checks new column, make index 0

            // move up, check column from up to down
            for (int r = 0; r < numRows; r++) {
                // if the value of the element is not 0, move element upward
                if (values[r][c] != 0) {
                    values[index][c] = values[r][c];
                    if(r != index) {
                        values[r][c] = 0;
                    }
                    index++;
                }
                // save old value in oldArr
                oldArr[r] = values[r][c];
            }

            // join up, if following two numbers are same, make new number which is two times of the number
            for(int r = 0 ; r < numRows - 1; r++){
                if(values[r][c] == values[r+1][c] & values[r][c] != 0){
                    values[r][c] += 1;

                    // if 2048 is generated, set is Cleared true
                    if((int)Math.pow(2, values[r][c]) == maxNum){
                        isCleared = true;
                    }

                    score += (int)Math.pow(2, values[r][c]);
                    // delete one number
                    values[r+1][c] = 0;
                    // check next two numbers
                    r++;
                }
            }

            index = 0;
            // move up
            for (int r = 0; r < numRows; r++) {
                if (values[r][c] != 0) {
                    values[index][c] = values[r][c];
                    if(r != index) {
                        values[r][c] = 0;
                    }
                    index++;
                }
            }

            // check if value is changed
            for(int r = 0; r < numRows; r++){
                if(oldArr[r] != values[r][c]){
                    isChanged = true;
                }
            }
        }

    }

    /**
     * move elements to down
     * move all elements downward and join following same numbers and move all elements downward again
     */
    public void moveDown(){
        int [] oldArr = new int[4];
        int index = 0;

        // move for each column
        for(int c = 0; c < numColumns; c++) {
            index = 3;

            // move down
            for (int r = 3; r >= 0; r--) {
                if (values[r][c] != 0) {
                    values[index][c] = values[r][c];
                    if(r != index) {
                        values[r][c] = 0;
                    }
                    index--;
                }
                oldArr[numRows - r - 1] = values[r][c];
            }


            // join down
            for(int r = numRows - 1 ; r > 0; r--){
                if(values[r][c] == values[r-1][c] & values[r][c] != 0){
                    values[r][c] += 1;
                    if((int)Math.pow(2, values[r][c]) == maxNum){
                        isCleared = true;
                    }
                    score += (int)Math.pow(2, values[r][c]);
                    values[r-1][c] = 0;
                    r--;
                }
            }

            index = 3;

            // move down
            for (int r = 3; r >= 0; r--) {
                if (values[r][c] != 0) {
                    values[index][c] = values[r][c];
                    if(r != index) {
                        values[r][c] = 0;
                    }
                    index--;
                }
            }

            // check if value is changed
            for(int r = 0; r < numRows; r++){
                if(oldArr[r] != values[numRows - r -1][c]){
                    isChanged = true;
                }
            }

        }

    }

    /**
     * move elements to left
     * move all elements to left and join following same numbers and move all elements to left again
     */
    public void moveLeft(){
        int [] oldArr = new int[4];
        int index = 0;

        // move for each row
        for(int r = 0; r < numRows; r++) {
            index = 0;

            // move left
            for (int c = 0; c < numColumns; c++) {
                if (values[r][c] != 0) {
                    values[r][index] = values[r][c];
                    if(c != index) {
                        values[r][c] = 0;
                    }
                    index++;
                }
                oldArr[c] = values[r][c];
            }

            // join left
            for(int c = 0 ; c < numColumns - 1; c++){
                if(values[r][c] == values[r][c+1] & values[r][c] != 0){
                    values[r][c] += 1;
                    if((int)Math.pow(2, values[r][c]) == maxNum){
                        isCleared = true;
                    }
                    score += (int)Math.pow(2, values[r][c]);
                    values[r][c+1] = 0;
                    c++;
                }
            }

            index = 0;

            // move left
            for (int c = 0; c < numColumns; c++) {
                if (values[r][c] != 0) {
                    values[r][index] = values[r][c];
                    if(c != index) {
                        values[r][c] = 0;
                    }
                    index++;
                }
            }

            // check if value is changed
            for(int c = 0; c < numColumns; c++){
                if(oldArr[c] != values[r][c]){
                    isChanged = true;
                }
            }
        }
    }

    /**
     * move elements to right
     * move all elements to right and join following same numbers and move all elements to right again
     */
    public void moveRight(){
        int [] oldArr = new int[4];
        int index = 0;

        // move for each row
        for(int r = 0; r < numRows; r++) {
            index = numRows - 1;

            // move left
            for (int c = numRows - 1; c >= 0; c--) {
                if (values[r][c] != 0) {
                    values[r][index] = values[r][c];
                    if(c != index) {
                        values[r][c] = 0;
                    }
                    index--;
                }
                oldArr[c] = values[r][c];
            }

            // join left
            for(int c = numRows - 1 ; c > 0; c--){
                if(values[r][c] == values[r][c-1] & values[r][c] != 0){
                    values[r][c] += 1;
                    if((int)Math.pow(2, values[r][c]) == maxNum){
                        isCleared = true;
                    }
                    score += (int)Math.pow(2, values[r][c]);
                    values[r][c-1] = 0;
                    c--;
                }
            }

            index = numRows - 1;

            // move left
            for (int c = numRows - 1; c >= 0; c--) {
                if (values[r][c] != 0) {
                    values[r][index] = values[r][c];
                    if(c != index) {
                        values[r][c] = 0;
                    }
                    index--;
                }
            }

            // check if value is changed
            for(int c = 0; c < numColumns; c++){
                if(oldArr[c] != values[r][c]){
                    isChanged = true;
                }
            }
        }
    }

    /**
     * get number of value in row and column of the values array
     * @param row
     * @param column
     * @return
     */
    public int getNum(int row, int column){
        int num;

        // if ArrayIndexOutOfBoundsException occurs, return 0
        try{
            num = values[row][column];
        } catch (ArrayIndexOutOfBoundsException ex) {
            return 0;
        }

        return num;
    }

    /**
     * check if the board is full
     * @return false if any element value is 0
     */
    public boolean isfull(){
        for(int i = 0; i < numRows; i++){
            for(int j = 0; j < numColumns; j++){
                if(values[i][j] == 0){
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * check if adjoining numbers are same
     * @return true if there are same following numbers
     */
    public boolean isMoveable(){
        // check up and down numbers
        for(int r = 0; r < numRows; r++){
            for(int c = 0; c < numColumns - 1; c++){
                if(values[r][c] == values[r][c+1]){
                    return true;
                }
            }
        }

        // check right and left numbers
        for(int c = 0; c < numColumns; c++){
            for(int r = 0; r < numRows - 1; r++){
                if(values[r][c] == values[r+1][c]){
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * make new board
     */
    public void randomize(){
        // remove all values
        values = new int[numRows][numColumns];
        score = 0;
        isCleared = false;
        isOver = false;

        // make two random numbers
        makeNewNum();
        makeNewNum();
    }

}
