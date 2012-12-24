/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.salaboy.rolo.arduino;

import javax.enterprise.inject.Produces;
import javax.inject.Named;
import javax.inject.Singleton;

/**
 *
 * @author salaboy
 */
@Singleton
public class ArduinoProvider {
    
    private ArduinoFirmata arduino = new ArduinoFirmata( ArduinoFirmata.list()[0], 57600);
    @Produces()
    @Named("arduino")
    
    
    public ArduinoFirmata getArduino(){
        return arduino;
    }
}
