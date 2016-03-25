package ie.dit;
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
    Slider turningSlider;

    // Constructor
    RemoteControl(PApplet papplet) {

        this.papplet = papplet;
        this.cp5 = new ControlP5(this.papplet); // Iniliatilze new control p5

        // Add motorSliders to cp5
        this.leftMotorSlider = cp5.addSlider("LeftMotor");
        this.rightMotorSlider = cp5.addSlider("RightMotor");
        this.turningSlider = cp5.addSlider("Turning");

        // Initialize the sliders
        initialize();
    }


    public void initialize()
    {
        // Sets sliders position, size, range and hides it
        this.leftMotorSlider.setPosition(papplet.width/10-20,papplet.height- (papplet.height/4)).setSize(20,100).setRange(0,255).hide();
        this.rightMotorSlider.setPosition(papplet.width-papplet.width/10,papplet.height- (papplet.height/4)).setSize(20,100).setRange(0,255).hide();
        this.turningSlider.setPosition(150,370).setWidth(400).setRange(255,0).setValue(128).setSliderMode(Slider.FLEXIBLE).setLabelVisible(false).hide();
    }

    public void render(char gear)
    {
        papplet.background(0);
        leftMotorSlider.show();
        rightMotorSlider.show();
        turningSlider.show();
        papplet.textSize(24);
        papplet.textAlign(papplet.CENTER);
        papplet.text("Gear: " + gear, papplet.width/2,papplet.height/2);
    }

    public void update(float speed, float leftspeed)
    {
        // Update controls
        rightMotorSlider.setValue(papplet.map(speed,0,200,0,255));
        leftMotorSlider.setValue(papplet.map(leftspeed,0,170,0,255));
        //turningSlider.setValue();

    }

}