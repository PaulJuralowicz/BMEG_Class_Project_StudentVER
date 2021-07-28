package Simulation;

import Util.Calculator;
import Util.Pair;

import java.util.ArrayList;

/**
 * The immune cell! It kills cancer, and has a chance to attack multiple cancer cells per turn! Pretty cooool
 */

public class ImmuneCell extends Cell{

    //white

    /**
     * Constructor
     * @param coords location
     */
    ImmuneCell(Pair coords){
        super(4, 3, coords);
    }

    /**
     * The logic of this little bugger. It has cancer in its sights, and it wants to KILL
     * @param neighbors all the cells in the world.
     */
    @Override
    public void interactNeighbors(ArrayList<Cell> neighbors){
        int index; //negative 1 cause we are a cancer cell, don't want to count twice
        ArrayList<Pair> cancer = new ArrayList<>();
        for(int x = -1; x< 2; x++){
            for(int y = -1; y < 2; y++){
                index = Calculator.indexFromCoord(coords.x - x, coords.y - y);
                if (index >= 0 && index < neighbors.size()){
                    if (neighbors.get(index).id == 3){
                        cancer.add(Calculator.coordFromIndex(index));
                    }
                }
            }
        }
        boolean keepFighting = true;
        while(cancer.size() != 0 && keepFighting){
            Pair toFight = cancer.get((int) (Math.random() * 100) % cancer.size());
            cancer.remove(toFight);
            neighbors.set(calc.indexFromCoord(toFight), new TissueCell(toFight)); // would make more sense to change to dead cell, but this keeps things interesting...
            if(Math.random() > 0.5){
                keepFighting = false;
            }
        }
    }
}