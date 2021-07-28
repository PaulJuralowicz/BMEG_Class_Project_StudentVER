package Simulation;

import Util.Calculator;
import Util.Pair;

import java.util.ArrayList;

/**
 *This is a cancer cell. It is the most complex cell as it can attack tissue or immune cells, or grow into a dead cell.
 * For attacking tissue, it is a 1 hit replace it with a dead cell.
 * Immune cells are cooler. Each hit from a cancer cell lowers its strength by 1. When an immune cell reaches 0 strength
 * it dies!
 *
 * It has a priority of action. If it can grow, it will grow. If it can kill a tissue cell, it will do that. Why?
 * Easiest way to grow is to kill a week tissue cell. If no other option, will attack immune cells. Path of
 * least resistance to growing basically.
 *
 * Growing means turning a dead cell into a CancerCell.
 */

public class CancerCell extends Cell{

    //green

    /**
     * Default constructor. Just places a cancer cell somewhere
     * @param coords the coords it is located at.
     */
    CancerCell(Pair coords){
        super(3, 1, coords);
    }

    /**
     * The big interaction method that is basically the action for the time step.
     * @param neighbors arraylist of all cells. Just keep stuff open for the student.
     */
    @Override
    public void interactNeighbors(ArrayList<Cell> neighbors){
        int index;
        ArrayList<Pair> dead = new ArrayList<>();
        ArrayList<Pair> tissue = new ArrayList<>();
        ArrayList<Pair> immune = new ArrayList<>();
        for(int x = -1; x< 2; x++){
            for(int y = -1; y < 2; y++){
                index = Calculator.indexFromCoord(coords.getX() - x, coords.getY() - y);
                if (index >= 0 && index < neighbors.size()){
                    if (neighbors.get(index).id == 0){
                        dead.add(Calculator.coordFromIndex(index));
                    } else if (neighbors.get(index).id == 1){
                        tissue.add(Calculator.coordFromIndex(index));
                    } else if (neighbors.get(index).id == 4){
                        immune.add(Calculator.coordFromIndex(index));
                    }
                }
            }
        }
        if (dead.size() > 0){
            //pick random and grow
            Pair toSpawn = dead.get((int) (Math.random() * 100) % dead.size());
            neighbors.set(Calculator.indexFromCoord(toSpawn), new CancerCell(toSpawn));
        } else if (tissue.size() >= immune.size() && tissue.size() != 0) {
            //pick random tissue and kick its ass.
            Pair toKill = tissue.get((int) (Math.random() * 100) % tissue.size());
            neighbors.set(Calculator.indexFromCoord(toKill), new DeadCell(toKill));
        } else if(immune.size() != 0){
            Pair toFight = immune.get((int) (Math.random() * 100) % immune.size());
            int fStr =neighbors.get(Calculator.indexFromCoord(toFight)).strength - 1;
            if (fStr <= 0){
                neighbors.set(Calculator.indexFromCoord(toFight), new DeadCell(toFight));
            } else {
                neighbors.get(Calculator.indexFromCoord(toFight)).strength = fStr;
            }
        }
    }
}
