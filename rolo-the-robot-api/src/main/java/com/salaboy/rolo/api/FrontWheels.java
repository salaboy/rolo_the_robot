/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.salaboy.rolo.api;

/**
 *
 * @author salaboy
 */
public interface FrontWheels {
    public void move(String direction, int distance);
    public void rotate(String direction, int degrees);
    
    public void setWheelsDistance(int distance);
    public void setWheelsDiameter(int diameter);
    
    void setName(String string);
    
    String getName();
}
