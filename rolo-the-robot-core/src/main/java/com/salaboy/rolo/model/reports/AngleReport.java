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
public class AngleReport {
    private String sensorName;
    private int degrees;

    public AngleReport() {
    }

    public String getSensorName() {
        return sensorName;
    }

    public void setSensorName(String sensorName) {
        this.sensorName = sensorName;
    }

    public int getDegrees() {
        return degrees;
    }

    public void setDegrees(int degrees) {
        this.degrees = degrees;
    }

    @Override
    public String toString() {
        return "AngleReport{" + "sensorName=" + sensorName + ", degrees=" + degrees + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + (this.sensorName != null ? this.sensorName.hashCode() : 0);
        hash = 79 * hash + this.degrees;
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
        final AngleReport other = (AngleReport) obj;
        if ((this.sensorName == null) ? (other.sensorName != null) : !this.sensorName.equals(other.sensorName)) {
            return false;
        }
        if (this.degrees != other.degrees) {
            return false;
        }
        return true;
    }
    
    
}
