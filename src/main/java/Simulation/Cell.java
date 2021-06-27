package Simulation;

import Util.Calculator;
import Util.Pair;

import java.util.ArrayList;

public class Cell {

    int id;
    Pair coords;
    int strength;
    Calculator calc;

    public Cell(int id, int strength, Pair coords){
        this.coords = coords;
        this.id = id;
        this.strength = strength;
    }

    public void interactNeighbors(ArrayList<Cell> neighbors, int width, int height){
        return;
    }
}
