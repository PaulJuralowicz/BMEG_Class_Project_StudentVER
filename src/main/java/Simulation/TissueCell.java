package Simulation;

import Util.Pair;

import java.util.ArrayList;

/**
 * A tissue cell. It wants to grow, but not as much as cancer. Has a chance to turn a dead
 * cell into a live one every time step
 */

public class TissueCell extends Cell{

    //red

    /**
     * you know the rules at this point. Constructor
     * @param coords location
     */
    TissueCell(Pair coords){
        super(1, 0, coords);
    }

    /**
     * The logic step. Looks at neighbouring dead cells, and grows into them
     * @param neighbors all the cells in the world.
     */
    @Override
    public void interactNeighbors(ArrayList<Cell> neighbors){
        int index; //negative 1 cause we are a cancer cell, don't want to count twice
        ArrayList<Pair> dead = new ArrayList<>();
        for(int x = -1; x< 2; x++) {
            for (int y = -1; y < 2; y++) {
                index = calc.indexFromCoord(coords.x - x, coords.y - y);
                if (index >= 0 && index < neighbors.size()) {
                    if (neighbors.get(index).id == 0) {
                        dead.add(calc.coordFromIndex(index));
                    }
                }
            }
        }
        if (dead.size() > 0 && Math.random() > 0.7){ //30% chance it actually will grow
            //pick random and grow
            Pair toSpawn = dead.get((int) (Math.random() * 100) % dead.size());
            neighbors.set(calc.indexFromCoord(toSpawn), new TissueCell(toSpawn));
        }
    }
}
