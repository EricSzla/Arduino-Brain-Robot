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
        //choice = 2;
        //choice = 3;
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
                turning('A');
            } else if (keyCode == 'D') {
                turning('D');

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

    public void movieEvent(Movie m) {
        m.read();
    }

    public static void main(String[] args) {
        PApplet.main(Main.class.getName());
    }


    // Use this code if HeadSet connected

    public void oscEvent(OscMessage msg) {
        if (choice == 1) {

            // if(frameCount % 20 == 0) {


            if (msg.checkAddrPattern("/muse/elements/experimental/concentration") == true) {
                cVar = msg.get(0).floatValue();
                //System.out.println("cVar: " + cVar);
                //System.out.println("checkC: " + checkC);

                System.out.println("cVar: " + cVar);
                    /*if((checkC > 0.5 && cVar < 0.5) || (checkC < 0.5 && cVar > 0.5))
                    {
                        passVar1 = "3C" + cVar + ",";
                        //headSet.pass(myPort, passVar, passVar1);
                        checkC = cVar;

                    }*/
            }

            if (cVar > 0.3) {
                myPort.clear();
                if (msg.checkAddrPattern("/muse/acc") == true) {

                    aVar = msg.get(2).floatValue();


                    if ((aVar < -100 && (checkA > -150 && checkA < 150) || (aVar < -150 && checkA > 150) || ((aVar > -150 && aVar < 150) && checkA < -150) || (aVar > -150 && aVar < 150) && checkA > 150) || (aVar > 150 && (checkA < 150 && checkA > -150)) || (aVar > 150 && checkA < -150)) {
                        passVar = "3A" + aVar + ",";

                        headSet.pass(myPort, passVar);

                        checkA = aVar;
                        checkcVar = cVar;

                        System.out.println("checkA: " + checkA);
                        System.out.println("aVar: " + aVar);
                    }/*else
                    {
                        passVar = "3A" + 100 + ",";
                        headSet.pass(myPort, passVar);
                    }*/

                }

            } else if (cVar < 0.3 && checkcVar > 0.3) {
                passVar = "3A" + 0 + ",";
                //headSet.pass(myPort, passVar);
                myPort.write("1L" + 0 + ",");
                myPort.write("1R" + 0 + ",");
                checkcVar = cVar;
                System.out.println("checkAblabla");
                checkA = 500;
            }
        }
    }
}

