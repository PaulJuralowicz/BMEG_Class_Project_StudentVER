package Simulation;

import Util.Pair;

import java.util.ArrayList;

public class CancerCell extends Cell{

    //green

    CancerCell(Pair coords){
        super(3, 1, coords);
    }

    @Override
    public void interactNeighbors(ArrayList<Cell> neighbors, int width, int height){
        int index; //negative 1 cause we are a cancer cell, don't want to count twice
        ArrayList<Pair> dead = new ArrayList<>();
        ArrayList<Pair> tissue = new ArrayList<>();
        ArrayList<Pair> immune = new ArrayList<>();
        for(int x = -1; x< 2; x++){
            for(int y = -1; y < 2; y++){
                index = calc.indexFromCoord(coords.x - x, coords.y - y);
                if (index >= 0 && index < width*height){
                    if (neighbors.get(index).id == 0){
                        dead.add(calc.coordFromIndex(index));
                    } else if (neighbors.get(index).id == 1){
                        tissue.add(calc.coordFromIndex(index));
                    } else if (neighbors.get(index).id == 4){
                        immune.add(calc.coordFromIndex(index));
                    }
                }
            }
        }
        if (dead.size() > 0){
            //pick random and grow
            Pair toSpawn = dead.get((int) (Math.random() * 100) % dead.size());
            neighbors.set(calc.indexFromCoord(toSpawn), new CancerCell(toSpawn));
        } else if (tissue.size() >= immune.size() && tissue.size() != 0) {
            //pick random tissue and kick its ass.
            Pair toKill = tissue.get((int) (Math.random() * 100) % tissue.size());
            neighbors.set(calc.indexFromCoord(toKill), new DeadCell(toKill));
        } else if(immune.size() != 0){
            Pair toFight = immune.get((int) (Math.random() * 100) % immune.size());
            int fStr =neighbors.get(calc.indexFromCoord(toFight)).strength - 1;
            if (fStr <= 0){
                neighbors.set(calc.indexFromCoord(toFight), new DeadCell(toFight));
            } else {
                neighbors.get(calc.indexFromCoord(toFight)).strength = fStr;
            }
        }
    }
}
