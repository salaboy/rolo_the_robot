/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.salaboy.rolo.api;

/**
 *
 * @author salaboy
 */
public interface Servo180 {

    void rotate(int degree);

    void setName(String string);

    String getName();

    void setPin(int pin);

    int getPin();
    
    int getCurrentDegree();
}
