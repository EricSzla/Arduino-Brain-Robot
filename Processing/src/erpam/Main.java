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
    Kinect kinect;

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
    int wdir = 0;

    public void settings() {
        size(displayWidth, displayHeight);

    }

    public void setup() {
        rectMode(CENTER);

        printArray(Serial.list()); // Prints the serial lists

        // Start the Bluetooth port
        String portName = Serial.list()[2]; //change the 0 to a 1 or 2 etc. to match your port

        try {
            myPort = new Serial(this, portName, 9600); // Initialize myPort and set the bit rate
        }catch (RuntimeException e)
        {
            e.printStackTrace();
        }

        frameRate(60);
        erpam = loadImage("erpam.png");

        BT = new RemoteControl(this);
        smooth();

        kinect = new Kinect(this);

        // Headset Connection
        try {
            oscp5 = new OscP5(this, 5000);
            headSet = new HeadSet(this);
        }catch (RuntimeException e)
        {
            e.printStackTrace();
        }
    }

    public void draw() {
        switch (choice) {
            case 0:
                textSize(12);
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
                headSet.updateSpeedo(sdir);
                headSet.updatePetrol(pdir);
                headSet.render();


                fill(255);
                rect(width/10,height/10,200,50);
                fill(0);
                text("Menu",width/10,height/10 + 10);
                break;
            case 4:
                kinect.render();
                kinect.control(myPort);
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
        BT.leftMotorSlider.hide();
        BT.rightMotorSlider.hide();

        image(erpam, width/2,erpam.height);

        if (x < width / 6) {
            x += 10;
        }

        fill(255);
        text("---> TERRITORY MAPPING", x, height / 3);

        rect(width/2,height/2,200,50);
        rect(width/2,height/2 + 100, 200, 50);
        rect(width/2,height/2 + 200, 200, 50);
        rect(width/2,height/2 + 300, 200, 50);

        fill(0);
        textAlign(CENTER);
        text("Bluetooth Control", width/2,height/2);
        text("AI Robot", width/2, height/2 + 100);
        text("Headset Control", width/2, height/2 + 200);
        text("Kinect Control", width/2, height/2 + 300);
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
        }else if(choice == 3)
        {
            // Emergency stop for headSet
            myPort.write("1L" + 0 + ",");
            myPort.write("1R" + 0 + ",");
        }


    }

    public void turning(char value) {
        if (value == 'A') {
            if (rightspeed >= 0 && leftspeed >= 50) {
                rightspeed = 250;
                leftspeed -= 50;
            } else if (rightspeed < 0 && leftspeed < 0) {
                rightspeed = -230;
                leftspeed += 50;
            }
        } else if (value == 'D') {
            if (rightspeed >= 50 && leftspeed >= 0) {
                leftspeed = 200;
                rightspeed -= 50;
            } else if (rightspeed < 0 && leftspeed < 0) {
                leftspeed = -200;
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
                rightspeed = 250;
                leftspeed = 200;
                currentGear = 'D';
            }
        } else if (value == 'S')  // Go backward
        {
            if (rightspeed <= 0 && leftspeed <= 0) {
                rightspeed = -230;
                leftspeed = -200;
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

            if(headSet.petrol == false) {
                if (msg.checkAddrPattern("/muse/elements/experimental/mellow") == true) {

                    System.out.println("Petrol: " + headSet.petrol);
                    float mVar = msg.get(0).floatValue();
                    // if (headSet.petrol == false) {
                    System.out.println("Mellow: " + mVar);
                    if (mVar == 1) {
                        pdir = 1;
                    } else {
                        pdir = 3;
                    }
                    //  }

                }
            }


            if (cVar > 0.3 && headSet.petrol == true ) {
                myPort.clear();
                pdir = 0;
                sdir = 1;
                headSet.checkPetrol = true;

                if (msg.checkAddrPattern("/muse/acc") == true) {
                    aVar = msg.get(2).floatValue();

                    if ((aVar < -100 && (checkA > -150 && checkA < 150) || (aVar < -150 && checkA > 150) || ((aVar > -150 && aVar < 150) && checkA < -150) || (aVar > -150 && aVar < 150) && checkA > 150) || (aVar > 150 && (checkA < 150 && checkA > -150)) || (aVar > 150 && checkA < -150)) {
                        passVar = "3A" + aVar + ",";
                        headSet.pass(myPort, passVar);
                        checkA = aVar;
                        checkcVar = cVar;
                        headSet.updateWheel(aVar);
                    }
                }


            } else if ((cVar < 0.3 && checkcVar > 0.3 || (headSet.petrol == false && headSet.checkPetrol == true) ) ){ //
                sdir = 0;
                pdir = 3;
                myPort.write("1L" + 0 + ",");
                myPort.write("1R" + 0 + ",");
                checkcVar = cVar;
                checkA = 500;
                currentGear = 'N';

                if((headSet.petrol == false && headSet.checkPetrol == true))
                {
                    headSet.checkPetrol = false;
                }
            }
        }
    }
}

