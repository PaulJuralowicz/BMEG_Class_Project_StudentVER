package Simulation;

import java.util.ArrayList;

import Util.Calculator;
import Util.Pair;

public class Logic {
    private int width;
    private int height;
    private ArrayList<Cell> cellList;
    private Calculator calc;

    public Logic(int width, int height){
        this.width = width;
        this.height = height;
        this.calc = new Calculator(width, height);
        cellList = new ArrayList<>();
        initialize();
    }

    private void initialize(){
        double rngNum =0;
        for(int i =0; i < width*height; i++){
            rngNum = Math.random()*100;
            if (rngNum < 90.0){
                cellList.add(new TissueCell(calc.coordFromIndex(i)));
            }
            else if (rngNum >= 90.0 && rngNum < 99.0){
                cellList.add(new ImmuneCell(calc.coordFromIndex(i)));
            } else {
                cellList.add(new CancerCell(calc.coordFromIndex(i)));
            }
        }
    }

    public void timeStep(){
        for(Cell c : cellList){
            c.interactNeighbors(cellList, width, height);
        }
    }

    public int setColour(Pair coords){
        int toRet = cellList.get(calc.indexFromCoord(coords)).id;
        return toRet;
    }
}
