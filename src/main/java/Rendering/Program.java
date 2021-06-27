/**
 *
 *
 * DONT TOUCH THIS PLEASE
 *
 *
 */

package Rendering;

import Simulation.Logic;
import Util.Calculator;
import Util.Pair;

import javax.swing.*;

public class Program {

    long timeSinceLastUpdate = 0;
    private final long FRAME_TIME = (long)((1.0/1.0)*1000000000.0); // set the denominator to desired frame rate

    private int width;
    private int height;
    private Calculator calc;

    public static void main(String[] args){
        Program program = new Program();
    }

    public Program(){
        onUserStart();
    }

    private void onUserStart(){
        width = 50;
        height = 50;
        calc = new Calculator(width,height);
        Logic programLogic = new Logic(width, height);
        int pixelWidth = 10;
        int pixelHeight = 10;
        javax.swing.JFrame frame = new javax.swing.JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize((width+2)*pixelWidth, (height+4)*pixelHeight);
        PixelPanel pixels = new PixelPanel(width, height, pixelWidth, pixelHeight);
        frame.getContentPane().add(pixels);
        frame.setVisible(true);
        pixels.repaint();
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
            System.out.println("NEXT");
            timeSinceLastUpdate = 0;
            Pair coords;
            programLogic.timeStep();
            for(int i = 0; i < pixels.canvasSize(); i++){
                coords = calc.coordFromIndex(i);
                pixels.getPixel(coords.x, coords.y).setColour(programLogic.setColour(coords));
            }
        }
    }
}
