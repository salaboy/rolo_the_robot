/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.salaboy.rolo.arduino;

import com.salaboy.rolo.api.LightSensor;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author salaboy
 */
@Arduino
public class ArduinoLightSensorImpl implements LightSensor {

    private String name;
    @Inject
    @Named("arduino")
    private ArduinoFirmata arduino;

    public ArduinoLightSensorImpl() {
        
    }
    
    @PostConstruct
    public void init(){
        System.out.println("INIT");
        arduino.pinMode(0, ArduinoFirmata.INPUT);
    }

    public ArduinoLightSensorImpl(String name) {
        this.name = name;
    }

    @Override
    public int readLight() {
        return arduino.analogRead(0);
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
