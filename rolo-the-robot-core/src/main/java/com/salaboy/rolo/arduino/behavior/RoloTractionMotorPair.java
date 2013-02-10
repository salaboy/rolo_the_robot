/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.salaboy.rolo.arduino.behavior;

import com.salaboy.rolo.api.Motor;
import com.salaboy.rolo.arduino.ArduinoMotor;

/**
 *
 * @author salaboy
 */
public interface RoloTractionMotorPair {
    public enum ROTATION {
        RIGHT, LEFT
    };

    void setRightMotor(ArduinoMotor right);
    void setLeftMotor(ArduinoMotor left);
    void rotate(ROTATION rotation);
    Motor getRightMotor();
    Motor getLeftMotor();
  
}
