/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.salaboy.rolo.events;

/**
 *
 * @author salaboy
 */
public class ReadDistanceSensorEvent {
    private String sensorName;
    private int distance;

    public ReadDistanceSensorEvent() {
    }

    public ReadDistanceSensorEvent(String sensorName, int distance) {
        this.sensorName = sensorName;
        this.distance = distance;
    }

    public String getSensorName() {
        return sensorName;
    }

    public void setSensorName(String sensorName) {
        this.sensorName = sensorName;
    }

 
    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }
    
    
}
