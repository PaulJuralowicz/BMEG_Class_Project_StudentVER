package Util;

/**
 * Utility class that can do calcs for you.
 *
 * I think as long as you initialize it once somewhere anything can use it, but I am not quite sure...
 */
public class Calculator {
    private static int width; //number of cells in x dir
    private static int height; //number of cells in y dir

    /**
     *  Sets up this bad boy in the typical manor
     * @param width
     * @param height
     */
    public Calculator(int width, int height){
        this.width = width;
        this.height = height;
    }

    /**
     * If you store cells in a 1D array, but want to translate that to a 2D pixel space / get neighbours in 2D, this
     * is how you do it.
     * @param x x coord
     * @param y y coord
     * @return the index in an array that corresponds to that x and y coord
     */
    public static int indexFromCoord(int x, int y){
        if(x >= width || y >= height){
            return -1;
        }
        return width*y + x;
    }

    /**
     * Same as the other but the coord is a Pair
     * @param coord coords
     * @return index in an array that corresponds to the coords
     */
    public static int indexFromCoord(Pair coord){
        return indexFromCoord(coord.x, coord.y);
    }

    /**
     * Sometimes when you have an index from an array, you want to know the location of the cell in 2D space. This
     * does that.
     * @param i index of cell in an array
     * @return Pair that is the coordinates of a cell in 2D space
     */
    public static Pair coordFromIndex(int i){
        Pair coords = new Pair();
        coords.x = i%width;
        coords.y = (i - coords.x)/width;
        return coords;
    }
}
