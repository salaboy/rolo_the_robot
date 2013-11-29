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
public class LightReport {
    private String sensorName;
    private int lightIntensity;

    public LightReport(String sensorName, int lightIntensity) {
        this.sensorName = sensorName;
        this.lightIntensity = lightIntensity;
    }

    public String getSensorName() {
        return sensorName;
    }

    public void setSensorName(String sensorName) {
        this.sensorName = sensorName;
    }

    public int getLightIntensity() {
        return lightIntensity;
    }

    public void setLightIntensity(int lightIntensity) {
        this.lightIntensity = lightIntensity;
    }

    @Override
    public String toString() {
        return "LightReport{" + "sensorName=" + sensorName + ", lightIntensity=" + lightIntensity + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 73 * hash + (this.sensorName != null ? this.sensorName.hashCode() : 0);
        hash = 73 * hash + this.lightIntensity;
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
        final LightReport other = (LightReport) obj;
        if ((this.sensorName == null) ? (other.sensorName != null) : !this.sensorName.equals(other.sensorName)) {
            return false;
        }
        if (this.lightIntensity != other.lightIntensity) {
            return false;
        }
        return true;
    }
    
    
}
