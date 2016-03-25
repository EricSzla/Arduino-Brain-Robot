package erpam;
import processing.core.*;
import processing.core.PImage;
import processing.serial.*;

/**
 * Created by Eryk Szlachetka and Pamela Sabio on 14/03/2016.
 **/

public class Main extends PApplet {

    // For Arduino connection
    Serial myPort;
    PImage erpam;

    public void settings()
    {
        size(700, 500);
    }

    public void setup()
    {
        printArray(Serial.list()); // Prints the serial lists

        // Start the Bluetooth port
        String portName = Serial.list()[2]; //change the 0 to a 1 or 2 etc. to match your port
        myPort = new Serial(this, portName, 9600); // Initialize myPort and set the bit rate

        frameRate(60);
        erpam = loadImage("erpam.png");

    }

    public void draw()
    {

    }

    public void menu()
    {
        background(0);
        //image(trailer,0,0,width,height);
        image(erpam, 0, 0);

        if (x < width/6)
        {
            x+=10;
        }

        fill(255);
        text("---> TERRITORY MAPPING", x, height/3);

    }

    public static void main(String[] args)
    {
        PApplet.main(Main.class.getName());
        /*String[] a = {"MAIN"};
        PApplet.runSketch( a, new Main());*/
    }
}

