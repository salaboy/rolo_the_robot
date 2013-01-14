/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.salaboy.rolo.arduino;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author salaboy
 */
@Arduino
public class ArduinoTouchSensorImpl implements ArduinoTouchSensor {

    private String name;
    @Inject
    @Named("arduino")
    private ArduinoFirmata arduino;
    
    private int pin = 15;

    public ArduinoTouchSensorImpl() {
        
    }
    
    @PostConstruct
    public void init(){
        arduino.pinMode(pin, ArduinoFirmata.INPUT);
    }

    @Override
    public int getPin() {
        return pin;
    }

    @Override
    public void setPin(int pin) {
        this.pin = pin;
    }

    public ArduinoTouchSensorImpl(String name) {
        this.name = name;
    }


    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean readTouch() {
         return (arduino.digitalRead(15)==0)?false:true;
    }
}
