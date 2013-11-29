/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.salaboy.rolo.model.reports;

/**
 *
 * @author salaboy
 */
public class DistanceReport {
    private String sensorName;
    private int distance;

    public DistanceReport(String sensorName, int distance) {
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

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + (this.sensorName != null ? this.sensorName.hashCode() : 0);
        hash = 29 * hash + this.distance;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DistanceReport other = (DistanceReport) obj;
        if ((this.sensorName == null) ? (other.sensorName != null) : !this.sensorName.equals(other.sensorName)) {
            return false;
        }
        if (this.distance != other.distance) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DistanceReport{" + "sensorName=" + sensorName + ", distance=" + distance + '}';
    }
    
    
}
