package Simulation;

import Util.Calculator;
import Util.Pair;

import java.util.ArrayList;

/**
 * The default, boring cell. Everything is public cause I am lazy, but the student would make things private.
 */

public class Cell {

    int id; //id of cell. I used this for the colour.
    Pair coords; //location
    int strength; //strength, used for combat
    Calculator calc; //calculator.

    /**
     * Constructor baby
     * @param id        the id of the cell
     * @param strength  the strength
     * @param coords    the location
     */
    public Cell(int id, int strength, Pair coords){
        this.coords = coords;
        this.id = id;
        this.strength = strength;
    }

    /**
     * Default version of the interaction. It does nothing
     * @param neighbors all the cells in the world.
     */
    public void interactNeighbors(ArrayList<Cell> neighbors){
    }
}
