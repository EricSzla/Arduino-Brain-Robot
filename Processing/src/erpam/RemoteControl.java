package erpam;
import processing.core.*;
import controlP5.*;

/**
 * Created by Eryk Szlachetka and Pamela Sabio on 19/03/2016.
 */

public class RemoteControl{

    // Declare Variables
    PApplet papplet;
    ControlP5 cp5;
    Slider leftMotorSlider;
    Slider rightMotorSlider;

    // Constructor
    RemoteControl(PApplet papplet) {

        this.papplet = papplet;
        this.cp5 = new ControlP5(this.papplet); // Iniliatilze new control p5

        // Add motorSliders to cp5
        this.leftMotorSlider = cp5.addSlider("LeftMotor");
        this.rightMotorSlider = cp5.addSlider("RightMotor");

        // Initialize the sliders
        initialize();
    }


    public void initialize()
    {
        // Sets sliders position, size, range and hides it
        this.leftMotorSlider.setPosition(papplet.width/10-20,papplet.height- (papplet.height/4));
        this.leftMotorSlider.setSize(20,100).setRange(0,255).hide();
        this.rightMotorSlider.setPosition(papplet.width-papplet.width/10,papplet.height- (papplet.height/4));
        this.rightMotorSlider.setSize(20,100).setRange(0,255).hide();
    }

    public void render(char gear)
    {

        papplet.background(0);
        // Show the motor sliders
        leftMotorSlider.show();
        rightMotorSlider.show();
        papplet.textSize(24);
        papplet.textAlign(papplet.CENTER);
        papplet.fill(255);
        papplet.text("Gear: " + gear, papplet.width/2,papplet.height/2);
        papplet.rect(papplet.width/10,papplet.height/10,200,50);
        papplet.fill(0);
        papplet.text("Menu",papplet.width/10,papplet.height/10 + 10);
    }

    public void update(float speed, float leftspeed)
    {
        // Update controls
        rightMotorSlider.setValue(papplet.map(speed,0,200,0,255));
        leftMotorSlider.setValue(papplet.map(leftspeed,0,170,0,255));

    }

}