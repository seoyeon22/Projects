package edu.angelo.finalprojecthong;

import java.util.ArrayList;
import java.util.List;

/**
 * Seoyeon Hong
 */

public class TargetSet2 {

    /**
     * list of target objects
     */
    public List<Target2> targets;

    /**
     * default constructor, call the other constructor with an argument of 10.
     */
    public TargetSet2(){
        this(2);
    }

    /**
     * constructor, initialize targets field with a new array list and add new target objects.
     * @param numTargets number of targets
     */
    public TargetSet2(int numTargets) {
        targets = new ArrayList<Target2>();

        for (int i = 0; i < numTargets; i++) {
            targets.add(new Target2());
        }


    }

    /**
     * call the advance method of each target object in targets to move the target objects.
     */
    public void advance(){
        // go through all of the Target objects in targets and move each target
        for(Target2 t: targets){
            t.advance();
        }
    }

}
