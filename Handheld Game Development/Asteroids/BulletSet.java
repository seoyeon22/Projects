package edu.angelo.finalprojecthong;

import java.util.ArrayList;
import java.util.List;

/**
 * Seoyeon Hong
 */

public class BulletSet {

    /**
     * list of bullet objects
     */
    public List<Bullet> bullets;

    /**
     *
     */
    public int index;

    /**
     * default constructor, call the other constructor with an argument of 20.
     */
    public BulletSet(){
        this(20);
    }

    /**
     * constructor, initialize targets field with a new array list and add new bullet objects.
     * @param numBullets number of bullets
     */
    public BulletSet(int numBullets) {
        bullets = new ArrayList<Bullet>();

        for (int i = 0; i < numBullets; i++) {
            bullets.add(new Bullet());
        }
        index = 0;

    }

    /**
     * call the advance method of each bullet object in bullets to move if draw variable is true.
     */
    public void advance(){
        // go through all of the Target objects in targets and move each target if draw is true.
        for(Bullet b: bullets){
            if(b.draw){
                b.advance();
            }
        }
    }



}
