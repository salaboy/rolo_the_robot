/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.salaboy.rolo.wedo.model;

/**
 *
 * @author salaboy
 */
public class DistanceReport {
    private int distance;

    public DistanceReport(int distance) {
        this.distance = distance;
    }

    public int getDistance() {
        return distance;
    }

    @Override
    public String toString() {
        return "DistanceReport{" + "distance=" + distance + '}';
    }
    
    
    
}
