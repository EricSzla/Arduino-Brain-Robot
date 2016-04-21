package erpam;
import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;
import processing.serial.Serial;

/**
 * Created by Eryk and Pamela on 02/04/2016.
 */
public class HeadSet {
    PApplet papplet;
    float radius;
    float fuelX;
    float fuelY;
    float theta;
    float meterTheta;
    float fuelLines;
    float x2;
    float y2;
    boolean petrol;

    float speedoX;
    float speedoY;
    float speedoTheta;
    float speedoTheta2;
    float speedox2;
    float speedoy2;
    PImage[] steeringWheel = new PImage[3];
    int rw;

    HeadSet(PApplet papplet) {

        this.papplet = papplet;
        this.radius = papplet.width/5;
        this.fuelX = papplet.width/1.15f;
        this.fuelY = papplet.height/1.4f;
        this.theta = 0;
        this.meterTheta = papplet.PI - 1;
        this.fuelLines = 16;
        this.x2 =  fuelX + papplet.sin(meterTheta + papplet.PI /2 - papplet.TWO_PI/70) * (radius / 2 - 10);
        this.y2 = fuelY -papplet.cos(meterTheta + papplet.PI/2 - papplet.TWO_PI/70) * (radius / 2 - 10);
        this.petrol = true;

        this.speedoX = papplet.width/2.2f;
        this.speedoY = papplet.height/1.8f;
        this.speedoTheta = 0;
        this.speedoTheta2 = papplet.PI;
        this.speedox2 =  speedoX + papplet.sin(speedoTheta2 + papplet.PI /2 - papplet.TWO_PI/70) * (radius - 5);
        this.speedoy2 = speedoY -papplet.cos(speedoTheta2 - papplet.PI/2 - papplet.TWO_PI/70) * (radius - 5);

        this.steeringWheel[0] = papplet.loadImage("wheel.png");
        this.steeringWheel[1] = papplet.loadImage("wheel.png");
        this.steeringWheel[2] = papplet.loadImage("wheel.png");
        for(int i =0 ;i < steeringWheel.length; i++)
        {
            this.steeringWheel[i].resize(papplet.width*2,papplet.height * 2);
        }

        this.rw = 0;

        papplet.imageMode(papplet.CENTER);
    }

    public void pass(Serial myPort,String var)
    {
        myPort.write(var);
    }
}

