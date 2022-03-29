package edu.angelo.finalprojecthong;

import android.util.Log;
import android.widget.Space;

/**
 * Seoyeon Hong
 * worked with Soi Kim
 *
 * To increase efficiency and lower power usage,
 * used static final for constants
 * and reused target objects by randomizing
 * and reused bullet objects by using boolean variable draw.
 *
 * Destroying small asteroid gives 10 points.
 * Destroying bigger asteroid gives 30 points, and it will be destroyed when it is hit twice.
 * As score increases, objects moves faster.
 * Game ends when spacecraft hits asteroid.
 * While the player presses the rotate and move button, spacecraft continues to rotate or move.
 */

public class World {
    public static final int WORLD_WIDTH = 320;
    public static final int WORLD_HEIGHT = 480;
    static final int SCORE_INCREMENT = 10;
    static final float TICK_INITIAL = 0.15f;
    static final float TICK_DECREMENT = 0.01f;

    public boolean gameOver = false;
    public int score = 0;
    float tickTime = 0;
    float tick = TICK_INITIAL;

    /**
     * as score get high, increase level and make asteroid's speed faster.
     */
    public int level = 1;

    /**
     * reference of TargetSet object
     */
    public TargetSet targetSet;

    /**
     * reference of TargetSet2 object
     */
    public TargetSet2 targetSet2;

    /**
     * reference of SpaceCraft object
     */
    public SpaceCraft spaceCraft;

    /**
     * reference of BulletSet object
     */
    public BulletSet bulletSet;


    public World() {
        /**
         * make new objects for reference variables.
         */
        targetSet = new TargetSet();
        targetSet2 = new TargetSet2();
        spaceCraft = new SpaceCraft();
        bulletSet = new BulletSet();
    }


    public void update(float deltaTime) {

        if (gameOver) {
            return;
        }

        tickTime += deltaTime;

        while (tickTime > tick) {
            tickTime -= tick;

            /**
             * change the location of moving objects.
             */
            targetSet.advance();
            targetSet2.advance();
            spaceCraft.advance();
            bulletSet.advance();

            /**
             * check if there is collision after moving objects.
             */
            checkCollision();

        }

        /**
         * increase level and speed of moving objects whenever the score increases by more than a certain score
         */
        if (level * 50 <= score && tick - TICK_DECREMENT > 0) {
            level += 1;
            tick -= TICK_DECREMENT;
        }

    }


    /**
     * within 16 pixels of the pixel shot at, remove the target and create new one at random location and add score
     */

    public void shoot(){

        /**
         * set bullet
         */
        for(int i = 0; i < bulletSet.bullets.size(); i++){

            /**
             * find a bullet object that is not used currently in use and then set the location and draw it on the screen.
             */
            if(!bulletSet.bullets.get(i).draw){
                bulletSet.bullets.get(i).locationX = spaceCraft.locationX;
                bulletSet.bullets.get(i).locationY = spaceCraft.locationY;
                bulletSet.bullets.get(i).angle = spaceCraft.angle;
                bulletSet.bullets.get(i).draw = true;
                return;
            }
        }
    }

    /**
     * check if spacecraft, bullet, asteroid hit each other.
     */
    public void checkCollision(){

        /**
         * distance between two objects.
         */
        double distance;

        /**
         * x, y coordinate of target
         */
        int tX, tY;

        /**
         *  true if Target object and spaceCraft hit each other.
         */
        boolean hit = false;

        /**
         *  true if Target2 object and spaceCraft hit each other.
         */
        boolean hit2 = false;

        /**
         * check if Target object and spaceCraft hit each other.
         */
        for(Target t : targetSet.targets){
            distance = (float)Math.sqrt(Math.pow(spaceCraft.locationX - t.locationX, 2) + Math.pow(spaceCraft.locationY - t.locationY, 2));
            if(distance < 30){
                gameOver = true;
            }
        }

        /**
         * check if Target2 object and spaceCraft hit each other.
         */
        for(Target2 t : targetSet2.targets){
            distance = (float)Math.sqrt(Math.pow(spaceCraft.locationX - t.locationX, 2) + Math.pow(spaceCraft.locationY - t.locationY, 2));
            if(distance < 40){
                gameOver = true;
            }
        }

        /**
         * look through all of the Target objects and check if target and bullet hit each other.
         */
        for(int i = 0; i < targetSet.targets.size(); i++){

            tX = targetSet.targets.get(i).locationX;
            tY = targetSet.targets.get(i).locationY;

            for(Bullet b : bulletSet.bullets){

                if(b.draw == true){ // check distance only when the bullet is drawn on the screen
                    distance = (int)Math.sqrt(Math.pow(b.locationX - tX, 2) + Math.pow(b.locationY - tY, 2)); // measure distance using the Euclidean distance formula.
                    if(distance <= 20){
                        targetSet.targets.get(i).randomize(); // move target to random location with random velocity when it is shot
                        b.draw = false; // remove bullet from the screen
                        hit = true;
                    }
                }
            }

        }

        /**
         * look through all of the Target2 objects and check if target and bullet hit each other.
         */
        for(int i = 0; i < targetSet2.targets.size(); i++){

            tX = targetSet2.targets.get(i).locationX;
            tY = targetSet2.targets.get(i).locationY;

            for(Bullet b : bulletSet.bullets){

                if(b.draw == true){  // check distance only when the bullet is drawn on the screen
                    distance = (int)Math.sqrt(Math.pow(b.locationX - tX, 2) + Math.pow(b.locationY - tY, 2)); // measure distance using the Euclidean distance formula.
                    if(distance <= 28){
                        b.draw = false; // remove bullet from the screen
                        targetSet2.targets.get(i).hitNum += 1;
                        if(targetSet2.targets.get(i).hitNum == 2){
                            targetSet2.targets.get(i).randomize(); // move target to random location with random velocity when it is shot
                            hit2 = true;
                        }
                    }
                }
            }
        }

        /**
         * increase score by SCORE_INCREMENT if Target object and bullet hit each other.
         */
        if(hit){
            score += SCORE_INCREMENT;
        }

        /**
         * increase score by 3 * SCORE_INCREMENT if Target2 object and bullet hit each other.
         */
        if(hit2){
            score += 3*SCORE_INCREMENT;
        }
    }
}
