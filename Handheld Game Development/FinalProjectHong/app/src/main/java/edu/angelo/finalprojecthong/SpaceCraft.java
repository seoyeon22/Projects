package edu.angelo.finalprojecthong;

import android.util.Log;

import java.util.Random;


public class SpaceCraft {

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
     * keep track of angle of spacecraft
     */
    public double angle;

    /**
     * keep track of how much spacecraft should move
     */
    public int len;

    /**
     * A Random object to reuse.
     */
    private static Random random = new Random();

    /**
     * The farthest left the center of a target can go, leaving room for the radius of the target.
     */
    public static final int minX = 17;

    /**
     * The farthest right the center of a target can go, leaving room for the radius of the target.
     */
    public static final int maxX = World.WORLD_WIDTH - 17;

    /**
     * The farthest up the center of a target can go, leaving room at the top of the screen for
     * pause and score and for the radius of the target.
     */
    public static final int minY = 17;

    /**
     * The farthest down the center of a target can go, leaving room for the radius of the target.
     */
    public static final int maxY = World.WORLD_HEIGHT - 17;

    /**
     * default constructor that calls randomize method.
     */
    public SpaceCraft(){
        locationX = (minX + maxX) / 2;
        locationY = (minY + maxY) / 2;
        velocityX = 0;
        velocityY = 0;
        angle = 90;
    }

    /**
     * move the target inside the world boundaries.
     */
    public void advance(){

        if(len - 2 > 0){
            len -= 2;

            velocityX = (int)(len * Math.cos(Math.toRadians(angle)));
            velocityY = - (int)(len * Math.sin(Math.toRadians(angle)));

            // move x coordinate
            int tmp = locationX + velocityX;
            if(minX <= tmp && tmp <= maxX){
                // add velocityX to locationX only if the target is between minX and maxX
                locationX = tmp;
            }
            else if(tmp > maxX){
                locationX = maxX;
                velocityX = 0;
            }
            else{
                locationX = minX;
                velocityX = 0;
            }

            // move y coordinate
            tmp = locationY + velocityY;
            if(minY <= tmp && tmp <= maxY){
                // add velocityY to locationY only if the target is between minY and maxY
                locationY = tmp;
            }
            else if(tmp > maxY){
                locationY = maxY;
                velocityY = 0;
            }
            else {
                locationY = minY;
                velocityY = 0;
            }
        }

    }




}
