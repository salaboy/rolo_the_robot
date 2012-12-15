package com.salaboy.rolo.ui;

/**
 * Hello world!
 *
 */
import cc.arduino.Arduino;
import processing.core.*;
import static processing.core.PGraphics.*;

public class MyProcessingArduinoOutSketch extends PApplet {

    Arduino arduino;
    int off = color(4, 79, 111);
    int on = color(84, 145, 158);
    int[] values = {Arduino.LOW, Arduino.LOW, Arduino.LOW, Arduino.LOW,
        Arduino.LOW, Arduino.LOW, Arduino.LOW, Arduino.LOW, Arduino.LOW,
        Arduino.LOW, Arduino.LOW, Arduino.LOW, Arduino.LOW, Arduino.LOW};

    public void setup() {

        size(470, 280);


        println(Arduino.list());
        arduino = new Arduino(this, Arduino.list()[0], 57600);


        arduino.pinMode(7, Arduino.OUTPUT); // Motor Signal 1
        arduino.pinMode(9, Arduino.OUTPUT); // Enable
        arduino.pinMode(11, Arduino.OUTPUT); // Motor Signal 2

    }

    public void draw() {
        delay(100);
       arduino.digitalWrite(7, Arduino.HIGH);
       arduino.digitalWrite(9, 126);
       arduino.digitalWrite(11, Arduino.LOW);
       delay(100);
       arduino.digitalWrite(7, Arduino.HIGH);
       arduino.digitalWrite(9, 126);
       arduino.digitalWrite(11, Arduino.LOW);
       
       
    }

    

    public static void main(String args[]) {
        PApplet.main(new String[]{"--present", "com.salaboy.rolo.ui.MyProcessingArduinoOutSketch"});
    }
}
