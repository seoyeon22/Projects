package edu.angelo.finalprojecthong;

import java.util.ArrayList;
import java.util.List;

/**
 * Seoyeon Hong
 */

public class TargetSet {

    /**
     * list of target objects
     */
    public List<Target> targets;

    /**
     * default constructor, call the other constructor with an argument of 6.
     */
    public TargetSet(){
        this(6);
    }

    /**
     * constructor, initialize targets field with a new array list and add new target objects.
     * @param numTargets number of targets
     */
    public TargetSet(int numTargets) {
        targets = new ArrayList<Target>();

        for (int i = 0; i < numTargets; i++) {
            targets.add(new Target());
        }
    }

    /**
     * call the advance method of each target object in targets to move the target objects.
     */
    public void advance(){
        // go through all of the Target objects in targets and move each target
        for(Target t: targets){
            t.advance();
        }
    }

}
