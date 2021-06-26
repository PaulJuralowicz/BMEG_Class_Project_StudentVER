/**
 *
 *
 * DONT TOUCH THIS PLEASE
 *
 *
 */

package Rendering;

import Simulation.Logic;

import javax.swing.*;

public class Program {

    long timeSinceLastUpdate = 0;
    private final long FRAME_TIME = (1/30)*1000000000; // set the denominator to desired frame rate

    private int width;
    private int height;

    public static void main(String[] args){
        Program program = new Program();
    }

    public Program(){
        onUserStart();
    }

    private int indexFromCoord(int x, int y){
        return width*y + x;
    }

    private Pair coordFromIndex(int i){
        Pair coords = new Pair();
        coords.x = i%width;
        coords.y = (i - coords.x)/width;
        return coords;
    }

    private void onUserStart(){
        width = 50;
        height = 50;
        Logic programLogic = new Logic(width, height);
        int pixelWidth = 10;
        int pixelHeight = 10;
        javax.swing.JFrame frame = new javax.swing.JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize((width+2)*pixelWidth, (height+4)*pixelHeight);
        PixelPanel pixels = new PixelPanel(width, height, pixelWidth, pixelHeight);
        frame.getContentPane().add(pixels);
        frame.setVisible(true);
        long currTime = 0;
        long nextTime = 0;
        while(true){
            currTime = System.nanoTime();
            onUserUpdate(pixels, currTime - nextTime, programLogic);
            pixels.repaint();
            nextTime = currTime;
        }
    }

    private void onUserUpdate(PixelPanel pixels, long timeEllapsed, Logic programLogic){
        if(timeSinceLastUpdate < FRAME_TIME){
            timeSinceLastUpdate += timeEllapsed;
        } else {
            timeSinceLastUpdate = 0;
            Pair coords;
            programLogic.timeStep();
            for(int i = 0; i < pixels.canvasSize(); i++){
                coords = coordFromIndex(i);
                pixels.getPixel(coords.x, coords.y).setColour(programLogic.setColour(coords.x,coords.y));
            }
        }
    }
}
