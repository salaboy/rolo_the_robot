/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.salaboy.rolo.arduino;

import com.salaboy.rolo.api.LightSensor;

/**
 *
 * @author salaboy
 */
public interface ArduinoLightSensor extends LightSensor {

    public int getPin();

    public void setPin(int pin);
}
