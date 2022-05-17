package edu.angelo.finalprojecthong;

import android.util.Log;

import java.util.Random;

public class Bullet {

    /**
     * keep track of where the x coordinate of center of the bullet is
     */
    public int locationX;

    /**
     * keep track of where the y coordinate of center of the bullet is
     */
    public int locationY;

    /**
     * keep track of angle of spacecraft
     */
    public double angle;

    /**
     * true if the bullet is inside the screen boundary and haven't hit asteroid after it is shot.
     */
    public boolean draw;


    /**
     * The farthest left the center of a bullet can go, leaving room for the radius of the target.
     */
    public static final int minX = 0;

    /**
     * The farthest right the center of a bullet can go, leaving room for the radius of the target.
     */
    public static final int maxX = World.WORLD_WIDTH;

    /**
     * The farthest up the center of a bullet can go, leaving room at the top of the screen for
     * pause and score and for the radius of the bullet.
     */
    public static final int minY = 0;

    /**
     * The farthest down the center of a bullet can go, leaving room for the radius of the target.
     */
    public static final int maxY = World.WORLD_HEIGHT;

    /**
     * default constructor that calls randomize method.
     */
    public Bullet(){
        draw = false;
    }

    /**
     * move the bullet inside the world boundaries.
     */
    public void advance(){

        if(draw){
            // move x coordinate
            int tmp = (int)(locationX + 20 * Math.cos(Math.toRadians(angle)));
            if(minX <= tmp && tmp <= maxX){
                // add velocityX to locationX only if the bullet is between minX and maxX
                locationX = tmp;
            }
            else{
                // don't draw if the bullet is out of the screen
                draw = false;
            }

            // move y coordinate
            tmp = (int)(locationY - 20 * Math.sin(Math.toRadians(angle)));
            if(minY <= tmp && tmp <= maxY){
                // add velocityY to locationY only if the bullet is between minY and maxY
                locationY = tmp;
            }
            else {
                // don't draw if the bullet is out of the screen
                draw = false;
        }



        }


    }

}
