package erpam;
import processing.core.*;
import processing.serial.Serial;
import processing.video.Movie;

/**
 * Created by Eryk Szlachetka and Pamela Sabio on 14/03/2016.
 **/

public class Main extends PApplet {

    // For Arduino connection
    Serial myPort;
    PImage erpam;
    int choice = 0;


    public void settings()
    {
        size(700, 500);
    }

    public void setup()
    {
        printArray(Serial.list()); // Prints the serial lists

        // Start the Bluetooth port
        String portName = Serial.list()[2]; //change the 0 to a 1 or 2 etc. to match the port for BT
        myPort = new Serial(this, portName, 9600); // Initialize myPort and set the bit rate

        frameRate(60);
        erpam = loadImage("erpam.png");

        smooth();
    }

    public void draw()
    {
    }

    public static void main(String[] args)
    {
        PApplet.main(Main.class.getName());
        /*String[] a = {"MAIN"};
        PApplet.runSketch( a, new Main());*/
    }
}

