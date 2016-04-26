package erpam;

import processing.core.*;
import processing.core.PImage;
import SimpleOpenNI.*;
import processing.serial.Serial;

/**
 * Created by Eryk and Pamela on 26/04/2016.
 */
public class Kinect {
    PApplet papplet;
    SimpleOpenNI context;
    PImage img;
    PVector projCoM, projHead, projLHand, projRHand, projLKnee, projRKnee;
    char value;
    boolean accelerationFlag, turningFlag;
    int leftspeed, rightspeed;

    Kinect(PApplet papplet){
        //set size of the application window

        this.papplet = papplet;
        //initialize context variable
        context = new SimpleOpenNI(papplet);

        //asks OpenNI to initialize and start receiving depth sensor's data
        context.enableDepth();

        //asks OpenNI to initialize and start receiving User data
        context.enableUser();

        //enable mirroring - flips the sensor's data horizontally
        context.setMirror(true);

        //... add more variable initialization code here...

        img=papplet.createImage(papplet.displayWidth,papplet.displayHeight,papplet.RGB);
        img.loadPixels();
        projCoM = new PVector();

        accelerationFlag = false;
        turningFlag = false;
        leftspeed = 0;
        rightspeed = 0;
    }

    public void render(){
        //clears the screen with the black color, this is usually a good idea
        //to avoid color artefacts from previous draw iterations
        papplet.background(255);
        //asks kinect to send new data
        context.update();

        //retrieves depth image
        PImage depthImage=context.depthImage();
        depthImage.loadPixels();

        //get user pixels - array of the same size as depthImage.pixels, that gives information about the users in the depth image:
        // if upix[i]=0, there is no user at that pixel position
        // if upix[i] > 0, upix[i] indicates which userid is at that position
        int[] upix=context.userMap();

        //colorize users
        for(int i=0; i < 1; i++){
            if(upix[i] > 0){
                //there is a user on that position
                //NOTE: if you need to distinguish between users, check the value of the upix[i]
                img.pixels[i]=papplet.color(0,0,255);
            }else{
                //add depth data to the image
                img.pixels[i]=depthImage.pixels[i];
            }
        }
        img.updatePixels();

        //draws the depth map data as an image to the screen
        //at position 0(left),0(top) corner
        //image(img,0,0);

        //draw significant points of users

        //get array of IDs of all users present
        int[] users=context.getUsers();

        papplet.ellipseMode(papplet.CENTER);

        //iterate through users
        for(int i=0; i < users.length; i++){
            int uid=users[i];

            //draw center of mass of the user (simple mean across position of all user pixels that corresponds to the given user)
            PVector realCoM=new PVector();

            //get the CoM in realworld (3D) coordinates
            context.getCoM(uid,realCoM);
            //PVector projCoM=new PVector();
            //convert realworld coordinates to projective (those that we can use to draw to our canvas)
            context.convertRealWorldToProjective(realCoM, projCoM);
            papplet.fill(255,0,0);
            papplet.ellipse(projCoM.x,projCoM.y,20,20);

            //check if user has a skeleton
            if(context.isTrackingSkeleton(uid)) {

                //draw head
                PVector realHead = new PVector();
                context.getJointPositionSkeleton(uid, SimpleOpenNI.SKEL_HEAD, realHead);
                projHead = new PVector();
                context.convertRealWorldToProjective(realHead, projHead);
                papplet.fill(255, 255, 0);
                papplet.ellipse(projHead.x, projHead.y, 20, 20);

                //draw left hand
                PVector realLHand = new PVector();
                context.getJointPositionSkeleton(uid, SimpleOpenNI.SKEL_LEFT_HAND, realLHand);
                projLHand = new PVector();
                context.convertRealWorldToProjective(realLHand, projLHand);
                papplet.fill(255, 255, 0);
                papplet.ellipse(projLHand.x, projLHand.y, 20, 20);

                //draw right hand
                PVector realRHand = new PVector();
                context.getJointPositionSkeleton(uid, SimpleOpenNI.SKEL_RIGHT_HAND, realRHand);
                projRHand = new PVector();
                context.convertRealWorldToProjective(realRHand, projRHand);
                papplet.fill(255);
                papplet.ellipse(projRHand.x, projRHand.y, 20, 20);

                //draw left knee
                PVector realLKnee = new PVector();
                context.getJointPositionSkeleton(uid, SimpleOpenNI.SKEL_LEFT_KNEE, realLKnee);
                projLKnee = new PVector();
                context.convertRealWorldToProjective(realLKnee, projLKnee);
                papplet.fill(0, 255, 0);
                papplet.ellipse(projLKnee.x, projLKnee.y, 20, 20);

                //draw right knee
                PVector realRKnee = new PVector();
                context.getJointPositionSkeleton(uid, SimpleOpenNI.SKEL_RIGHT_KNEE, realRKnee);
                projRKnee = new PVector();
                context.convertRealWorldToProjective(realRKnee, projRKnee);
                papplet.fill(255, 0, 255);
                papplet.ellipse(projRKnee.x, projRKnee.y, 20, 20);
            }

        }

    }

    //is called everytime a new user appears
    public void onNewUser(SimpleOpenNI curContext, int userId)
    {
        //asks OpenNI to start tracking a skeleton data for this user
        //NOTE: you cannot request more than 2 skeletons at the same time due to the perfomance limitation
        //      so some user logic is necessary (e.g. only the closest user will have a skeleton)
        curContext.startTrackingSkeleton(userId);
    }

    //is called everytime a user disappears
    public void onLostUser(SimpleOpenNI curContext, int userId) {
        papplet.text("SHOW YOURSELF ! ", papplet.width / 2, papplet.height / 2);
    }

    public void control(Serial myPort)
    {
        //if left hand is below the head and right hand is above the head then make the robot go forward
        if(projLHand.y < projHead.y && projRHand.y > projHead.y)
        {
            accelerationFlag = true;
            value = 'W';
        }

        //if left hand is above the head and right hand is below the head then make the robot go backward
        else if(projLHand.y > projHead.y && projRHand.y < projHead.y)
        {
            accelerationFlag = true;
            value = 'S';
        }

        //if left knee is above the hips then turn left
        if(projLKnee.y < projCoM.y)
        {
            turningFlag = true;
            value = 'A';
        }

        //if right knee is above the hips then turn left
        else if(projRKnee.y > projCoM.y)
        {
            turningFlag = true;
            value = 'D';
        }

        if(accelerationFlag == true)
        {
            acceleration();
            accelerationFlag = false;
        }
        if(turningFlag == true)
        {
            turning();
            turningFlag = false;
        }

        myPort.write("4L" + leftspeed + ",");
        myPort.write("4R" + rightspeed + ",");
    }

    public void acceleration() {
        if (value == 'W') // Go forward
        {
            if (rightspeed < 0 || leftspeed < 0) {
                rightspeed = 0;
                leftspeed = 0;

            } else {
                rightspeed = 250;
                leftspeed = 200;
            }
        } else if (value == 'S')  // Go backward
        {
            if (rightspeed <= 0 && leftspeed <= 0) {
                rightspeed = -230;
                leftspeed = -200;
            } else {
                rightspeed = 0;
                leftspeed = 0;
            }
        }
    }

    public void turning() {
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
}
