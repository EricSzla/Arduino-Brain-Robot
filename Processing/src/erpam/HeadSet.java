package erpam;
import processing.core.PApplet;
import processing.serial.Serial;

/**
 * Created by Eryk on 02/04/2016.
 */
public class HeadSet {
    PApplet papplet;

    HeadSet(PApplet papplet) {

        this.papplet = papplet;
    }

    public void pass(Serial myPort,String var)
    {
        myPort.write(var);
    }
}
