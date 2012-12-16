/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.salaboy.rolo.arduino;

import com.salaboy.rolo.api.Motor;

/**
 *
 * @author salaboy
 */
public interface ArduinoMotor extends Motor{
    void setupMotor(int signal1, int signal2, int pwm);
}
