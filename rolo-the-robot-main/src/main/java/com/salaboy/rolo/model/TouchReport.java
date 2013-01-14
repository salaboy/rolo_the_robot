/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.salaboy.rolo.model;

/**
 *
 * @author salaboy
 */
public class TouchReport {
    private boolean pressed;
    private String sensorName;
    
    public TouchReport(String sensorName, boolean pressed) {
        this.sensorName = sensorName;
        this.pressed = pressed;
    }

    public boolean getPressed() {
        return this.pressed;
    }

    public void setPressed(boolean pressed) {
        this.pressed = pressed;
    }

    public String getSensorName() {
        return sensorName;
    }

    public void setSensorName(String sensorName) {
        this.sensorName = sensorName;
    }

    @Override
    public String toString() {
        return "TouchReport{" + "pressed=" + pressed + ", sensorName=" + sensorName + '}';
    }

 
}
