/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.salaboy.rolo.arduino.simple;

import javax.enterprise.inject.Produces;
import javax.inject.Singleton;

/**
 *
 * @author salaboy
 */
@Singleton
public class RoloFeatureSerialProvider {

    private static RoloFeatureSerial arduino = new RoloFeatureSerial("/dev/tty.usbmodemfa131",
                //RoloFeatureSerial.list()[0], 
                115200);

    
    @Produces()
    public RoloFeatureSerial getArduino() {
        RoloFeatureSerial.list();
        return arduino;
    }
}
