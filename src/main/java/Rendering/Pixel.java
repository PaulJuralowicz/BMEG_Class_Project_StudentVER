/**
 *
 *
 * DONT TOUCH THIS PLEASE
 *
 *
 */

package Rendering;

import Util.Pair;

import java.awt.Component;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;


public class Pixel extends Component{

    private final int x;
    private final int y;
    private final Pair coords;
    int colourID;
    Color[] colors = new Color[5];
    private final int w;
    private final int h;

    /**
     * Constructor for fun stuff Shows ya what colors should be
     * @param x
     * @param y
     * @param w
     * @param h
     * @param colourID
     */
    Pixel(int x, int y, int w, int h, int colourID){
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        coords = new Pair(x,y);
        this.colourID = colourID;
        colors[0] = Color.BLACK;
        colors[1] = Color.RED;
        colors[2] = Color.BLUE;
        colors[3] = Color.GREEN;
        colors[4] = Color.WHITE;
    }

    public void setColour(int colourID){
        if(colourID <= 4 && colourID >= 0){
            this.colourID = colourID;
        }
    }

    public Pair getCoords(){
        return coords;
    }

    /**
     * Paints a pixel on the screen
     * @param g the graphic to paint on.
     */
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g.setColor(colors[colourID]);
        g.fillRect(x,y,w,h);

    }
}
