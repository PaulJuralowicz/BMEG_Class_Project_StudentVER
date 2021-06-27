/**
 *
 *
 * DONT TOUCH THIS PLEASE
 *
 *
 */

package Rendering;

import Util.Pair;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PixelPanel extends JPanel {

    private ArrayList<Pixel> pixels;
    private int width;
    private int height;

    private Pair coordFromIndex(int i){
        Pair coords = new Pair();
        coords.x = i%width;
        coords.y = (i - coords.x)/width;
        return coords;
    }

    public PixelPanel(int w, int h, int pixelWidth, int pixelHeight){
        this.width = w;
        this.height = h;
        pixels = new ArrayList();
        Pair currCoords;
        for(int i = 0; i < width*height; i++){
            currCoords = coordFromIndex(i);
            pixels.add(new Pixel(currCoords.x*pixelWidth, currCoords.y*pixelHeight,pixelWidth, pixelHeight, 0));
        }
    }
    public void paintComponent(Graphics g){
        for(Pixel p : pixels){
            p.draw(g);
        }
    }
    public Pixel getPixel(int x, int y){
        return pixels.get(x + (y * width));
    }
    public int canvasSize(){
        return(width*height);
    }
}
