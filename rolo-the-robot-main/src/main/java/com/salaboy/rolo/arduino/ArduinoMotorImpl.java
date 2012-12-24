/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.salaboy.rolo.arduino;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author salaboy
 */
@Arduino
public class ArduinoMotorImpl implements ArduinoMotor {

    private String name;
    @Inject
    @Named("arduino")
    private ArduinoFirmata arduino;

    private int signal1; 
    private int signal2; 
    private int pwm;
    
    private boolean running = false;
    
    static long defaultLatency = 100;
    
    public ArduinoMotorImpl() {
        
    }
    /*
     * We need to setup the correct pins to use for each motor
     * In my configuration I have
     * MotorA:  s1: 7, s2: 11,  -> Enable (pwm) 5 
     * MotorB:  s1: 6, s2: 12,  -> Enable (pwm)10
     */
    @Override
    public void setupMotor(int signal1, int signal2, int pwm){
        this.signal1 = signal1;
        this.signal2 = signal2;
        this.pwm = pwm;
        
        arduino.pinMode(this.signal1, ArduinoFirmata.OUTPUT); 
        arduino.pinMode(this.signal2, ArduinoFirmata.OUTPUT);
        arduino.pinMode(this.pwm, ArduinoFirmata.OUTPUT);
    }
    
    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void forward(int speed, long millisec) {
        arduino.digitalWrite(this.signal1, ArduinoFirmata.HIGH);
        arduino.digitalWrite(this.signal2, ArduinoFirmata.LOW);
        arduino.digitalWrite(this.pwm, speed);
        this.running = true;
        try {
            Thread.sleep(millisec);
        } catch (InterruptedException ex) {
            Logger.getLogger(ArduinoMotorImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        arduino.digitalWrite(this.signal1, ArduinoFirmata.LOW);
        arduino.digitalWrite(this.signal2, ArduinoFirmata.LOW);
        arduino.digitalWrite(this.pwm, 0);
        this.running = false;
    }

    @Override
    public void backward(int speed, long millisec) {
        arduino.digitalWrite(this.signal1, ArduinoFirmata.LOW);
        arduino.digitalWrite(this.signal2, ArduinoFirmata.HIGH);
        arduino.digitalWrite(this.pwm, speed);
        this.running = true;
        try {
            Thread.sleep(millisec);
        } catch (InterruptedException ex) {
            Logger.getLogger(ArduinoMotorImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        arduino.digitalWrite(this.signal1, ArduinoFirmata.LOW);
        arduino.digitalWrite(this.signal2, ArduinoFirmata.LOW);
        arduino.digitalWrite(this.pwm, 0);
        this.running = false;
    }

    @Override
    public void start(int speed, DIRECTION dir) {
        
        if(dir.equals(DIRECTION.FORWARD)){
            arduino.digitalWrite(this.signal1, ArduinoFirmata.HIGH);
            arduino.digitalWrite(this.signal2, ArduinoFirmata.LOW);
        }else if(dir.equals(DIRECTION.BACKWARD)){
            arduino.digitalWrite(this.signal1, ArduinoFirmata.LOW);
            arduino.digitalWrite(this.signal2, ArduinoFirmata.HIGH);
        }
        arduino.digitalWrite(this.pwm, speed);
        this.running = true;
    }

    @Override
    public void stop() {
        arduino.digitalWrite(this.signal1, ArduinoFirmata.LOW);
        arduino.digitalWrite(this.signal2, ArduinoFirmata.LOW);
        arduino.digitalWrite(this.pwm, 0);
        this.running = false;
    }

    @Override
    public boolean isRunning() {
        return running;
    }

    @Override
    public void setRunning(boolean running) {
        this.running = running;
    }
}
