/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.salaboy.rolo.arduino;

import com.salaboy.rolo.api.UltraSonicSensor;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author salaboy
 */
@Arduino
public class UltraSonicSensorImpl implements UltraSonicSensor {

    private String name;
    @Inject
    @Named("arduino")
    private ArduinoFirmata arduino;

    public UltraSonicSensorImpl() {
        
    }
    
    @PostConstruct
    public void init(){
       
        arduino.pinMode(5, ArduinoFirmata.INPUT);
    }

    public UltraSonicSensorImpl(String name) {
        this.name = name;
    }

    @Override
    public int readDistance() {
        return arduino.analogRead(5);
        
        
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
