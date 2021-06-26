package Simulation;

public class Logic {
    private int width;
    private int height;

    public Logic(int width, int height){
        this.width = width;
        this.height = height;
    }

    public void timeStep(){

    }

    public int setColour(int x, int y){
        return (int)((Math.random()*100)%5);
    }
}
