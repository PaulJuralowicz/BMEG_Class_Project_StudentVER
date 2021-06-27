package Util;

public class Calculator {
    private static int width;
    private static int height;

    public Calculator(int width, int height){
        this.width = width;
        this.height = height;
    }

    public static int indexFromCoord(int x, int y){
        if(x >= width || y >= height){
            return -1;
        }
        return width*y + x;
    }

    public static int indexFromCoord(Pair coord){
        return indexFromCoord(coord.x, coord.y);
    }

    public static Pair coordFromIndex(int i){
        Pair coords = new Pair();
        coords.x = i%width;
        coords.y = (i - coords.x)/width;
        return coords;
    }
}
