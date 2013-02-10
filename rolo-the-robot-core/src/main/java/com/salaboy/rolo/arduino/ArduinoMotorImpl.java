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
    private DIRECTION currentDirection = DIRECTION.NONE;
    static long defaultLatency = 100;

    public ArduinoMotorImpl() {
    }
    /*
     * We need to setup the correct pins to use for each motor
     * In my configuration I have
     * MotorA:  s1: 7, s2: 11,  -> Enable (pwm) 5 
     *   new:
     * MotorB:  s1: 6, s2: 12,  -> Enable (pwm)10
     *   new: s1: 4, s2: 12 -> 10
     * 
     * Motor C(arm): s1: 2, s2: 13, -> Enable (pwm)6
     */

    @Override
    public void setupMotor(int signal1, int signal2, int pwm) {
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
    public void forward(final int speed, final long millisec) {
        final int localSignal1 = this.signal1;
        final int localSignal2 = this.signal2;
        final int localPWM = this.pwm;
        new Thread(new Runnable() {
            @Override
            public void run() {
                arduino.digitalWrite(localSignal1, ArduinoFirmata.HIGH);
                arduino.digitalWrite(localSignal2, ArduinoFirmata.LOW);
                arduino.digitalWrite(localPWM, speed);
                running = true;
                currentDirection = DIRECTION.FORWARD;
                try {
                    Thread.sleep(millisec);
                } catch (InterruptedException ex) {
                    Logger.getLogger(ArduinoMotorImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
                arduino.digitalWrite(localSignal1, ArduinoFirmata.LOW);
                arduino.digitalWrite(localSignal2, ArduinoFirmata.LOW);
                arduino.digitalWrite(localPWM, 0);
                running = false;
                currentDirection = DIRECTION.NONE;

            }
        }).start();

    }

    @Override
    public void backward(final int speed, final long millisec) {
        final int localSignal1 = this.signal1;
        final int localSignal2 = this.signal2;
        final int localPWM = this.pwm;
        new Thread(new Runnable() {
            @Override
            public void run() {
                arduino.digitalWrite(localSignal1, ArduinoFirmata.LOW);
                arduino.digitalWrite(localSignal2, ArduinoFirmata.HIGH);
                arduino.digitalWrite(localPWM, speed);
                running = true;
                currentDirection = DIRECTION.BACKWARD;
                try {
                    Thread.sleep(millisec);
                } catch (InterruptedException ex) {
                    Logger.getLogger(ArduinoMotorImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
                arduino.digitalWrite(localSignal1, ArduinoFirmata.LOW);
                arduino.digitalWrite(localSignal2, ArduinoFirmata.LOW);
                arduino.digitalWrite(localPWM, 0);
                running = false;
                currentDirection = DIRECTION.NONE;
            }
        }).start();
        
    }

    @Override
    public void start(int speed, DIRECTION dir) {

        if (dir.equals(DIRECTION.FORWARD)) {
            arduino.digitalWrite(this.signal1, ArduinoFirmata.HIGH);
            arduino.digitalWrite(this.signal2, ArduinoFirmata.LOW);
            currentDirection = DIRECTION.FORWARD;
        } else if (dir.equals(DIRECTION.BACKWARD)) {
            arduino.digitalWrite(this.signal1, ArduinoFirmata.LOW);
            arduino.digitalWrite(this.signal2, ArduinoFirmata.HIGH);
            currentDirection = DIRECTION.BACKWARD;
        }
        arduino.digitalWrite(this.pwm, speed);
        this.running = true; 
    }

    @Override
    public void stop() {
        arduino.digitalWrite(this.signal1, ArduinoFirmata.LOW);
        arduino.digitalWrite(this.signal2, ArduinoFirmata.LOW);
        arduino.digitalWrite(this.pwm, 0);
        currentDirection = DIRECTION.NONE;
        this.running = false;
    }

    @Override
    public boolean isRunning() {
        return running;
    }

    public DIRECTION getCurrentDirection() {
      return currentDirection;
    }
 
    @Override
    public void setRunning(boolean running) {
        this.running = running;
    }

    @Override
    public int getSignal1() {
      return this.signal1;
    }

    @Override
    public int getSignal2() {
      return this.signal2;
    }

    @Override
    public int getPwm() {
      return this.pwm;
    }
    
    @Override
    public String toString() {
        return "ArduinoMotorImpl["+this.hashCode()+"]{" + "name=" + name + ", arduino=" + arduino + ", signal1=" + signal1 + ", signal2=" + signal2 + ", pwm=" + pwm + ", running=" + running + '}';
    }
    
    
}
