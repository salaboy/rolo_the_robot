package com.salaboy.rolo.ui;

/**
 * Hello world!
 *
 */
import cc.arduino.Arduino;
import processing.core.*;
import static processing.core.PGraphics.*;


public class MyProcessingArduinoSketch extends PApplet {

    Arduino arduino;
    int  off = color(4, 79, 111);
    int  on = color(84, 145, 158);
    
    public void setup() {
        
        size(470, 280);
        arduino = new Arduino(this, Arduino.list()[0], 57600);

        for (int i = 0; i <= 13; i++) {
            arduino.pinMode(i, Arduino.INPUT);
        }
    }

    public void draw() {

        background(off);
        stroke(on);

        for (int i = 0; i <= 13; i++) {
            if (arduino.digitalRead(i) == Arduino.HIGH) {
                fill(on);
            } else {
                fill(off);
            }

            rect(420 - i * 30, 30, 20, 20);
        }

        for (int i = 0; i <= 5; i++) {
            ellipse(280 + i * 30, 240, arduino.analogRead(i) / 16, arduino.analogRead(i) / 16);
        }


    }

    public static void main(String args[]) {
        PApplet.main(new String[]{"--present", "com.salaboy.rolo.ui.MyProcessingArduinoSketch"});
    }
}
