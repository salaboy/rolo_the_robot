/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.salaboy.rolo.arduino;

import com.salaboy.rolo.api.Servo180;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author salaboy
 */
@Arduino
public class ArduinoServo180Impl implements Servo180 {

    private String name;
    
    @Inject
    @Named("arduino")
    private ArduinoFirmata arduino;
    
    private int pin = 3;
    
    private int currentDegree = 0;

    public ArduinoServo180Impl() {
    }

    @PostConstruct
    public void init(){
        arduino.pinMode(pin, ArduinoFirmata.SERVO);
    }
    
    public ArduinoServo180Impl(String name) {
        this.name = name;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public int getPin() {
        return pin;
    }
   
    @Override
    public void rotate(int degree) {
        currentDegree = degree;
        arduino.analogWrite(pin, currentDegree);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCurrentDegree() {
        return currentDegree;
    }
    
    @PreDestroy
    public void destroy(){
        arduino.pinMode(3, ArduinoFirmata.LOW);
    }
    
    
    
}
