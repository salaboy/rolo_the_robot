/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.salaboy.rolo.model;

/**
 *
 * @author salaboy
 */
public class LightReport {
    private int light;
    private String sensorName;
    
    public LightReport(String sensorName, int light) {
        this.sensorName = sensorName;
        this.light = light;
    }

    public int getLight() {
        return light;
    }

    public void setLight(int light) {
        this.light = light;
    }

    public String getSensorName() {
        return sensorName;
    }

    public void setSensorName(String sensorName) {
        this.sensorName = sensorName;
    }

    @Override
    public String toString() {
        return "LightReport{" + "light=" + light + ", sensorName=" + sensorName + '}';
    }

 
}
