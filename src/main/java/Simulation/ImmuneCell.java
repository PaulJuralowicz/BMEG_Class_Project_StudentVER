package Simulation;

import Util.Pair;

import java.util.ArrayList;

public class ImmuneCell extends Cell{

    //white

    ImmuneCell(Pair coords){
        super(4, 3, coords);
    }

    @Override
    public void interactNeighbors(ArrayList<Cell> neighbors, int width, int height){
        int index; //negative 1 cause we are a cancer cell, don't want to count twice
        ArrayList<Pair> cancer = new ArrayList<>();
        for(int x = -1; x< 2; x++){
            for(int y = -1; y < 2; y++){
                index = calc.indexFromCoord(coords.x - x, coords.y - y);
                if (index >= 0 && index < width*height){
                    if (neighbors.get(index).id == 3){
                        cancer.add(calc.coordFromIndex(index));
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