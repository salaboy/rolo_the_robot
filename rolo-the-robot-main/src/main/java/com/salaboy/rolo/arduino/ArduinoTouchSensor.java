/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.salaboy.rolo.arduino;

import com.salaboy.rolo.api.TouchSensor;

/**
 *
 * @author salaboy
 */
public interface ArduinoTouchSensor extends TouchSensor {

    public int getPin();

    public void setPin(int pin);
}
