package Simulation;

import Util.Pair;

import java.util.ArrayList;

public class TissueCell extends Cell{

    //red

    TissueCell(Pair coords){
        super(1, 0, coords);
    }

    @Override
    public void interactNeighbors(ArrayList<Cell> neighbors, int width, int height){
        int index; //negative 1 cause we are a cancer cell, don't want to count twice
        ArrayList<Pair> dead = new ArrayList<>();
        for(int x = -1; x< 2; x++) {
            for (int y = -1; y < 2; y++) {
                index = calc.indexFromCoord(coords.x - x, coords.y - y);
                if (index >= 0 && index < width * height) {
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
