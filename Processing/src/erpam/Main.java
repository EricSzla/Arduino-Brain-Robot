package erpam;
import processing.core.*;
import processing.core.PImage;
import processing.video.*;
import processing.serial.*;

/**
 * Created by Eryk Szlachetka and Pamela Sabio on 14/03/2016.
 **/

public class Main extends PApplet {

    // For Arduino connection
    Serial myPort;
    RemoteControl BT;

    Movie trailer;
    //boolean[] keys = new boolean[512];

    PImage erpam;
    int x = 0;

    int choice = 0;
    float speed = 0;
    float leftspeed = 0;
    char currentGear = 'N';

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

        //trailer = new Movie(this,"trailer.mp4");
        //trailer.loop()

        BT = new RemoteControl(this);
        smooth();
    }

    public void draw()
    {
        switch(choice)
        {
            case 0:
                menu();
                break;
            case 1:
                BT.render(currentGear);
                break;
            case 2:
                //mapping();
                break;
            default:
                menu();
        }

    }

    public void mouseClicked()
    {
        // To be completed
        choice = 1;
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

    public void keyPressed() {

        //float value = map(valueReturned, 90, 230, 60, 230);
        //String passValue = "L" + value;
        // keys[keyCode] = true; Slows down the reaction time ?
        if(choice == 1) {
            if (keyCode == 'W') {

                if(speed < 0 || leftspeed < 0)
                {
                    speed = 0;
                    leftspeed = 0;
                    currentGear = 'N';

                }else {
                    speed = 200;
                    leftspeed = 170;
                    currentGear = 'D';
                }

            } else if (keyCode == 'S') {
                if (speed == 0 && leftspeed == 0) {
                    speed = -200;
                    leftspeed = -190;
                    currentGear = 'R';
                } else {
                    speed = 0;
                    leftspeed = 0;
                    currentGear = 'N';
                }
            } else if (keyCode == 'A') {
                if(speed >= 0 && leftspeed >= 50) {
                    speed = 200;
                    leftspeed -= 50;
                }else if(speed < 0 && leftspeed < 0)
                {
                    speed = -200;
                    leftspeed += 50;
                }
            } else if (keyCode == 'D') {
                if(speed >= 50 && leftspeed >= 0) {
                    leftspeed = 170;
                    speed -= 50;
                }else if(speed < 0 && leftspeed < 0)
                {
                    leftspeed = -190;
                    speed += 50;
                }

            } else {
                System.out.print("im in else");
                leftspeed = 0;
                speed = 0;
            }

            myPort.write("R" + speed + ",");
            myPort.write("L" + leftspeed + ",");

            BT.update(speed,leftspeed);

        }


    }

    public void motorSpeeds(){
        /*
            This method has to read in a value from Arduino and set the leftMotor and rightMotor slider value to the passed value e.g.
            if(myPort.available() > 0)
            {
                if(strcmp(var,0,1) == 'L') // OR SOMETHING LIKE THAT TO BE CHECKED

                if(var.read() >= 0)
                {
                    LeftMotor(var.read());
                }
r
                if(var2.read() >=0)
                {
                    // HAS TO BE MAPPED AS ONE MOTOR IS FASTER THAN ANOTHER !
                    // TO BE CHECKED IF ITS LEFT OR RIGHT !                    <<<------------------------
                    float rightSpeed = map(var2.read(),0,200,0,255);
                    RightMotor(rightSpeed);
                }
            }

            Arduino should have a code like:
            var.write(leftMotorCurrentSpeed);
            var2.write(rightMotorCurrentSpeed);
         */
    }

    public void Turning(float value)
    {
        /*
            This method should set the turning slide depending on either:
                - Keyboard input e.g.  input = A then decrease the slider,  input == D then increase the slider
                - Difference between Left and Right motor's speed. Use map function?


            if(keyPressed == 'D')
            {
                Turning(--value);
            }else if(keyPressed == 'A')
            {
                Turning(--value);
            }

         */
    }
    public void movieEvent(Movie m)
    {
        m.read();
    }

    public static void main(String[] args)
    {
        PApplet.main(Main.class.getName());
        /*String[] a = {"MAIN"};
        PApplet.runSketch( a, new Main());*/
    }
}

