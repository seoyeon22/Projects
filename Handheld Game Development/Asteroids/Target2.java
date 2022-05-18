package edu.angelo.finalprojecthong;

/**
 * Seoyeon Hong
 */

import java.util.Random;

public class Target2 {
    /**
     *
     */
    public int hitNum;

    /**
     * keep track of where the x coordinate of center of the target is
     */
    public int locationX;

    /**
     * keep track of where the y coordinate of center of the target is
     */
    public int locationY;

    /**
     * keep track of how fast the target is going in which direction in the x-axis
     */
    public int velocityX;

    /**
     * keep track of how fast the target is going in which direction in the y-axis
     */
    public int velocityY;

    /**
     * A Random object to reuse.
     */
    private static Random random = new Random();

    /**
     * The farthest left the center of a target can go, leaving room for the radius of the target.
     */
    public static final int minX = 0;

    /**
     * The farthest right the center of a target can go, leaving room for the radius of the target.
     */
    public static final int maxX = World.WORLD_WIDTH + 16;

    /**
     * The farthest up the center of a target can go, leaving room at the top of the screen for
     * pause and score and for the radius of the target.
     */
    public static final int minY = 0;

    /**
     * The farthest down the center of a target can go, leaving room for the radius of the target.
     */
    public static final int maxY = World.WORLD_HEIGHT + 16;

    /**
     * default constructor that calls randomize method.
     */
    public Target2(){
        hitNum = 0;
        randomize();
    }

    /**
     * move the target inside the world boundaries.
     */
    public void advance(){
        // move x coordinate
        int tmp = locationX + velocityX;
        if(minX <= tmp && tmp <= maxX){
            // add velocityX to locationX only if the target is between minX and maxX
            locationX = tmp;
        }
        else{
            randomize();
        }

        // move y coordinate
        tmp = locationY + velocityY;
        if(minY <= tmp && tmp <= maxY){
            // add velocityY to locationY only if the target is between minY and maxY
            locationY = tmp;
        }
        else{
            randomize();
        }
    }

    /**
     * randomize target's coordinate
     */
    public void randomize(){
        /**
         * set random location
         */
        int rm = random.nextInt(4);
        switch(rm){
            // comes from the upper boundary of the screen
            case 0:
                locationX = random.nextInt(maxX - minX) + minX;
                locationY = 0;

                velocityX = random.nextInt(10) - 5;
                velocityY = random.nextInt(5);
                break;

            // comes from the right boundary of the screen
            case 1:
                locationX = maxX;
                locationY = random.nextInt(maxY - minY) + minY;

                velocityX = random.nextInt(5) - 5;
                velocityY = random.nextInt(10) - 5;
                break;

            // comes from the lower boundary of the screen
            case 2:
                locationX = random.nextInt(maxX - minX) + minX;
                locationY = maxY;

                velocityX = random.nextInt(10) - 5;
                velocityY = random.nextInt(5) - 5;
                break;

            // comes from the left boundary of the screen
            case 3:
                locationX = 0;
                locationY = random.nextInt(maxY - minY) + minY;

                velocityX = random.nextInt(5);
                velocityY = random.nextInt(10) - 5;
                break;
        }

        hitNum = 0;
    }
}
