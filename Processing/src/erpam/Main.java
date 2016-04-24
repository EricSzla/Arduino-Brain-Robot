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
    String passVar = "";
    HeadSet headSet;
    float checkcVar = 0;
    int pdir = 0;
    int sdir = 0;

    public void settings() {
        size(displayWidth, displayHeight);
    }

    public void setup() {
        printArray(Serial.list()); // Prints the serial lists
        String portName = Serial.list()[2]; //change the 0 to a 1 or 2 etc. to match your port

        try {
            myPort = new Serial(this, portName, 9600); // Initialize myPort and set the bit rate
        }catch (RuntimeException e)
        {
            e.printStackTrace();
        }

        // Headset Connection
        try {
            oscp5 = new OscP5(this, 5000);
            headSet = new HeadSet(this);
        }catch (RuntimeException e)
        {
            e.printStackTrace();
        }

        rectMode(CENTER);
        frameRate(60);
        erpam = loadImage("erpam.png");
        BT = new RemoteControl(this);
        smooth();


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
                background(0);
                fill(255);
                text("Press any key to start..", width/2,height/2);
                rect(width/10,height/10,200,50);
                fill(0);
                text("Menu",width/10,height/10 + 10);
                break;
            case 3:

                // Consider using those methods for headset
                //headSet.updateSpeedo(sdir);
                //headSet.updateWheel(200);
                //headSet.render();
                //headSet.updatePetrol(pdir);

                fill(255);
                rect(width/10,height/10,200,50);
                fill(0);
                text("Menu",width/10,height/10 + 10);
                break;
            default:
                menu();
        }

    }

    public void mouseClicked() {

        if(choice == 0) {
            if (mouseX >= width / 2 - 100 && mouseX <= width / 2 + 100) // Check if mouse is in the middle
            {
                if (mouseY >= height / 2 - 25 && mouseY <= height / 2 + 25) // Check if mouse is in the first square
                {
                    choice = 1;
                } else if (mouseY >= height / 2 + 75 && mouseY <= height / 2 + 125) {
                    choice = 2;
                } else if (mouseY >= height / 2 + 175 && mouseY <= height / 2 + 225) {
                    choice = 3;
                }
            }
        }else
        {
            // If go back to menu button pressed, change the choice.
            if(mouseX < width/10 + 100 && mouseX > width/10 - 100)
            {
                if(mouseY < height/10 +25 && mouseY > height/10 - 25)
                {
                    choice = 0;
                }
            }
        }
    }

    public void menu() {
        background(0);
        image(erpam, width/2,erpam.height);

        rect(width/2,height/2,200,50);
        rect(width/2,height/2 + 100, 200, 50);
        rect(width/2,height/2 + 200, 200, 50);

        fill(0);
        textAlign(CENTER);
        text("Bluetooth Control", width/2,height/2);
        text("AI Robot", width/2, height/2 + 100);
        text("Headset Control", width/2, height/2 + 200);
    }

    public void keyPressed() {
        if(choice == 2)
        {
            myPort.write("2ff,");
        }
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
        if (choice == 3) {
            if (msg.checkAddrPattern("/muse/elements/experimental/concentration") == true) {
                cVar = msg.get(0).floatValue();
                System.out.println("C: " + cVar);
            }

            /*if(headSet.petrol == false)
            {
                 if(msg.checkAddrPattern("/muse/elements/experimental/mellow") == true)
                 {
                     float mVar = msg.get(0).floatValue();
                     System.out.println(mVar);
                     if(mVar == 1)
                     {
                         pdir = 1;
                     }else
                     {
                         pdir = 3;
                     }

                 }
            }*/

            if (cVar > 0.3 ) {//&& headSet.petrol == true) {
                myPort.clear();
                pdir = 0;
                sdir = 1;
                if (msg.checkAddrPattern("/muse/acc") == true) {
                    aVar = msg.get(2).floatValue();

                    if ((aVar < -100 && (checkA > -150 && checkA < 150) || (aVar < -150 && checkA > 150) || ((aVar > -150 && aVar < 150) && checkA < -150) || (aVar > -150 && aVar < 150) && checkA > 150) || (aVar > 150 && (checkA < 150 && checkA > -150)) || (aVar > 150 && checkA < -150)) {
                        passVar = "3A" + aVar + ",";
                        headSet.pass(myPort, passVar);
                        checkA = aVar;
                        checkcVar = cVar;
                    }
                }



            } else if (cVar < 0.3 && checkcVar > 0.3 ){//|| headSet.petrol == false) {
                sdir = 0;
                myPort.write("1L" + 0 + ",");
                myPort.write("1R" + 0 + ",");
                checkcVar = cVar;
                checkA = 500;
                currentGear = 'N';
            }
        }
    }
}

