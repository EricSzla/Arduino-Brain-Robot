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
    boolean checkPetrol;

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
        this.checkPetrol = false;

        this.speedoX = papplet.width/2.2f;
        this.speedoY = papplet.height/1.8f;
        this.speedoTheta = 0;
        this.speedoTheta2 = papplet.PI;
        this.speedox2 =  speedoX + papplet.sin(speedoTheta2 + papplet.PI /2 - papplet.TWO_PI/70) * (radius - 5);
        this.speedoy2 = speedoY -papplet.cos(speedoTheta2 - papplet.PI/2 - papplet.TWO_PI/70) * (radius - 5);

        this.steeringWheel[0] = papplet.loadImage("wheel.png");
        this.steeringWheel[1] = papplet.loadImage("left.png");
        this.steeringWheel[2] = papplet.loadImage("right.png");

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

    public void render() {
        papplet.background(0);
        papplet.pushMatrix();
        papplet.fill(0);
        papplet.noStroke();
        papplet.text("Petrol", fuelX,fuelY + 30 );
        papplet.ellipse(fuelX, fuelY, radius, radius);

        for (int i = 0; i < fuelLines; i++) {

            if(theta > papplet.PI/2 + 0.5 && theta <= papplet.PI + 0.01)
            {
                papplet.stroke(0,255, 0);

            }else if(theta > papplet.PI/2 - 0.5 && theta < papplet.PI - 0.01)
            {
                papplet.stroke(255,255,0);
            }else
            {
                papplet.stroke(255,0,0);
            }
            float lineX = fuelX + papplet.sin(theta - papplet.PI /2) * (radius / 2 - 5);
            float lineY = fuelY - papplet.cos(theta - papplet.PI/2) * (radius /  2 - 5);

            papplet.line(lineX, lineY, fuelX, fuelY);

            theta += papplet.TWO_PI / fuelLines;
            if (theta > papplet.PI) {
                theta = 0;
            }
        }
        papplet.fill(0);
        papplet.stroke(0);
        papplet.ellipse(fuelX,fuelY,radius/1.5f,radius/1.5f);
        papplet.stroke(255);
        papplet.strokeWeight(3);
        papplet.ellipse(fuelX,fuelY,5,5);
        papplet.line(fuelX,fuelY,x2,y2);
        papplet.strokeWeight(2);
        papplet.popMatrix();


        papplet.pushMatrix();

        //papplet.ellipse(speedoX, speedoY, radius*3f, radius*3f);

        float thetaInc = papplet.TWO_PI/fuelLines;
        float r;
        for (int i = 1; i < fuelLines; i++) {


            speedoTheta = i * thetaInc;

            if (speedoTheta > papplet.PI) {
                speedoTheta = 0;
            }

            if(i%2 == 0) {
                r = radius/1.5f;
            }
            else
            {
                r = radius /1.05f;
            }

            float lineX1 = speedoX + papplet.sin(speedoTheta - papplet.PI /2) * (radius * 1.3f - 5);
            float lineY1 = speedoY - papplet.cos(speedoTheta - papplet.PI/2) * (radius * 1.3f - 5);

            float lineX2 = speedoX + papplet.sin(speedoTheta - papplet.PI /2) * (r);
            float lineY2 = speedoY - papplet.cos(speedoTheta - papplet.PI/2) * (r);

            papplet.stroke(255);
            papplet.line(lineX1, lineY1, lineX2, lineY2);
            //papplet.line(speedoX, speedoY, lineX, lineY);

        }
        papplet.fill(0);
        papplet.stroke(0);
        papplet.stroke(255,0,0);
        papplet.strokeWeight(5);
        papplet.ellipse(speedoX,speedoY,10,10);
        papplet.line(speedoX,speedoY,speedox2,speedoy2);
        papplet.strokeWeight(2);
        papplet.popMatrix();

        papplet.pushMatrix();
        papplet.image(steeringWheel[rw],papplet.width/2+20,papplet.height-100);
        papplet.popMatrix();

    }

    public void updateWheel(float dir)
    {
        if(dir > 150)
        {
            rw = 2;
        }else if(dir < -150)
        {
            rw = 1;
        }else
        {
            rw = 0;
        }
    }


    public void updatePetrol(int dir)
    {
        if(dir == 0) {
            if(meterTheta >= 0.05f) {
                meterTheta -= 0.005f;
            }else
            {
                petrol = false;
                papplet.fill(255,0,0);
                papplet.text("Petrol not available !", papplet.width/1.2f, papplet.height/1.55f);
            }
        }else if(dir == 1)
        {
            if(meterTheta <= papplet.PI) {
                meterTheta += 0.01f;
            }else
            {
                checkPetrol = false;
                petrol = true;
                papplet.fill(255);
                papplet.text("Petrol available !", papplet.width/1.2f, papplet.height/1.55f);
            }
        }

        this.x2 =  fuelX + papplet.sin(meterTheta - papplet.PI /2 - papplet.TWO_PI/70) * (radius / 2 - 10);
        this.y2 = fuelY - papplet.cos(meterTheta - papplet.PI/2 - papplet.TWO_PI/70) * (radius / 2 - 10);
    }

    public void updateSpeedo(int dir)
    {
        if(dir == 0)
        {
            if(speedoTheta2 <= papplet.PI) {
                speedoTheta2 += 0.01f;
            }
        }else if(dir == 1)
        {
            if(speedoTheta2 >= 0.1f) {
                speedoTheta2 -= 0.01f;
            }
        }

        this.speedox2 =  speedoX + papplet.sin(speedoTheta2 + papplet.PI /2 - papplet.TWO_PI/70) * (radius - 5);
        this.speedoy2 = speedoY -papplet.cos(speedoTheta2 - papplet.PI/2 - papplet.TWO_PI/70) * (radius - 5);
    }
}
