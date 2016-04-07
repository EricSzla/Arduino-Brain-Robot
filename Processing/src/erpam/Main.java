package erpam;

import processing.core.*;
import processing.core.PImage;
import processing.video.*;
import processing.serial.*;
import oscP5.*;

/**
 * Created by Eryk Szlachetka and Pamela Sabio on 14/03/2016.
 **/

public class Main extends PApplet {

    // For Arduino connection
    Serial myPort;
    RemoteControl BT;

    Movie trailer; // Background movie

    PImage erpam;  // Menu image
    int x = 0;     // Used for animation in menu

    int choice = 0;         // User choice (menu)
    float rightspeed = 0;   // Right speed of the motor
    float leftspeed = 0;    // Left speed of the motor
    char currentGear = 'N';


    // Use this code if HeadSet connected
    //  muse-io --device Muse --osc osc.udp://localhost:5000
    OscP5 oscp5;
    float cVar = 0; // Concetration data
    float aVar = 0; // Accelometer data
    float checkA = 800;
    float checkC = 1;
    String passVar = "";
    String passVar1 = "";
    HeadSet headSet;
    float checkcVar = 0;

    public void settings() {
        size(displayWidth, displayHeight);
    }

    public void setup() {
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

        // Use this code if HeadSet connected

        oscp5 = new OscP5(this, 5000);
        headSet = new HeadSet(this);
    }


    public void draw() {
        switch (choice) {
            case 0:
                menu();
                break;
            case 1:
                BT.render(currentGear);
                break;
            case 2:
                //ai()
                break;
            case 3:
                //headSet()
                break;
            default:
                menu();
        }
    }

    public void mouseClicked() {
        // To be completed
        choice = 1;
    }

    public void menu() {
        background(0);
        //image(trailer,0,0,width,height);
        image(erpam, 0, 0);

        if (x < width / 6) {
            x += 10;
        }

        fill(255);
        text("---> TERRITORY MAPPING", x, height / 3);

    }

    public void keyPressed() {
        if (choice == 1) {
            if (keyCode == 'W') {
                acceleration('W');
            } else if (keyCode == 'S') {
                acceleration('S');
            } else if (keyCode == 'A') {
                //turning('A');
            } else if (keyCode == 'D') {
                //turning('D');

            } else {
                leftspeed = 0;
                rightspeed = 0;
            }

            myPort.write("1L" + leftspeed + ",");
            myPort.write("1R" + rightspeed + ",");

            BT.update(rightspeed, leftspeed);
        }


    }

    public void turning(char value) {
        if (value == 'A') {
            if (rightspeed >= 0 && leftspeed >= 50) {
                rightspeed = 220;
                //rightspeed = 200;
                leftspeed -= 50;
            } else if (rightspeed < 0 && leftspeed < 0) {
                rightspeed = -200;
                leftspeed += 50;
            }
        } else if (value == 'D') {
            if (rightspeed >= 50 && leftspeed >= 0) {
                //leftspeed = 170;
                leftspeed = 200;
                rightspeed -= 50;
            } else if (rightspeed < 0 && leftspeed < 0) {
                leftspeed = -190;
                rightspeed += 50;
            }
        }
    }

    public void acceleration(char value) {
        if (value == 'W') // Go forward
        {
            if (rightspeed < 0 || leftspeed < 0) {
                rightspeed = 0;
                leftspeed = 0;
                currentGear = 'N';

            } else {
                //rightspeed = 200;
                //leftspeed = 170;
                rightspeed = 220;
                leftspeed = 200;
                currentGear = 'D';
            }
        } else if (value == 'S')  // Go backward
        {
            if (rightspeed <= 0 && leftspeed <= 0) {
                rightspeed = -200;
                leftspeed = -190;
                currentGear = 'R';
            } else {
                rightspeed = 0;
                leftspeed = 0;
                currentGear = 'N';
            }
        }
    }

    // Use this code if HeadSet connected

    public void oscEvent(OscMessage msg) {
        if (choice == 1) {

            if (msg.checkAddrPattern("/muse/elements/experimental/concentration") == true) {
                cVar = msg.get(0).floatValue();
                //System.out.println("cVar: " + cVar);
                //System.out.println("checkC: " + checkC);

                System.out.println("cVar: " + cVar);
            }
        }
    }

            public void motorSpeeds () {
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

        public void movieEvent (Movie m)
        {
            m.read();
        }

        public static void main (String[]args)
        {
            PApplet.main(Main.class.getName());
        /*String[] a = {"MAIN"};
        PApplet.runSketch( a, new Main());*/
        }
    }

