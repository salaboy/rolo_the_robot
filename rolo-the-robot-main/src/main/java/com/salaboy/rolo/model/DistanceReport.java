/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.salaboy.rolo.model;

/**
 *
 * @author salaboy
 */
public class DistanceReport {
    private int distance;
    private String sensorName;
    
    public DistanceReport(String sensorName, int distance) {
        this.sensorName = sensorName;
        this.distance = distance;
    }

    public int getDistance() {
        return distance;
    }

    public String getSensorName() {
        return sensorName;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public void setSensorName(String sensorName) {
        this.sensorName = sensorName;
    }
    
    @Override
    public String toString() {
        return "DistanceReport{" + "distance=" + distance + ", sensorName=" + sensorName + '}';
    }
    
 
}
