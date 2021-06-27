package Simulation;

import Util.Pair;

/**
 * This cell is dead and does nothing but looks ok
 */
public class DeadCell extends Cell{

    //black

    /**
     * Constructor
     * @param coords location
     */
    DeadCell(Pair coords){
        super(0, 0, coords);
    }
}
