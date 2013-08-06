/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.salaboy.rolo.arduino.simple;

import com.salaboy.rolo.api.FrontWheels;
import javax.inject.Inject;

/**
 *
 * @author salaboy
 */
public class SimpleSerialFrontWheels implements FrontWheels{
    private String name;
    
    @Inject
    private RoloFeatureSerial roloSerial;
    
    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

   

    @Override
    public void rotate(String direction, int degrees) {
        roloSerial.sendMessage(name+":rotate:2:"+direction+":"+degrees);
    }

    @Override
    public void move(String direction, int distance) {
        roloSerial.sendMessage(name+":move:2:"+direction+":"+distance);
    }

    @Override
    public void setWheelsDistance(int distance) {
        roloSerial.sendMessage(name+":setDist:1:"+distance);
    }

    @Override
    public void setWheelsDiameter(int diameter) {
        roloSerial.sendMessage(name+":setDiam:1:"+diameter);
    }

    
}
