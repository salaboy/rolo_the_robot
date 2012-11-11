/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.salaboy.rolo.wedo.model;

import com.salaboy.rolo.wedo.api.DistanceSensor;
import com.salaboy.rolo.wedo.api.Motor;
import com.salaboy.rolo.wedo.api.TiltSensor;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author salaboy
 */
public class RoloTheRobot {

    private String name;
    private Map<String, Motor> motors = new HashMap<String, Motor>();
    private Map<String, DistanceSensor> distanceSensors = new HashMap<String, DistanceSensor>();
    private Map<String, TiltSensor> tiltSensors = new HashMap<String, TiltSensor>();

    public RoloTheRobot(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Motor> getMotors() {
        return motors;
    }
    
    public Motor getMotorByName(String name) {
        return motors.get(name);
    }

    public void setMotors(Map<String, Motor> motors) {
        this.motors = motors;
    }

    public void addMotor(String name, Motor motor) {
        this.motors.put(name, motor);
    }

    public Map<String, DistanceSensor> getDistanceSensors() {
        return distanceSensors;
    }

    public DistanceSensor getDistanceSensorByName(String name) {
        return distanceSensors.get(name);
    }

    public void setDistanceSensors(Map<String, DistanceSensor> distanceSensors) {
        this.distanceSensors = distanceSensors;
    }

    public void addDistanceSensor(String name, DistanceSensor distanceSensor) {
        this.distanceSensors.put(name, distanceSensor);
    }

    public Map<String, TiltSensor> getTiltSensors() {
        return tiltSensors;
    }
    
    public TiltSensor getTiltSensorByName(String name) {
        return tiltSensors.get(name);
    }

    public void setTiltSensors(Map<String, TiltSensor> tiltSensors) {
        this.tiltSensors = tiltSensors;
    }

    public void addTiltSensor(String name, TiltSensor tiltSensor) {
        this.tiltSensors.put(name, tiltSensor);
    }

    public int getNumberOfMotors() {
        return motors.size();
    }

    public int getNumberOfDistanceSensors() {
        return distanceSensors.size();
    }

    public int getNumberOfTiltSensors() {
        return tiltSensors.size();
    }

    public boolean hasMotors() {
        return !motors.isEmpty();
    }

    public boolean hasTiltSensors() {
        return !tiltSensors.isEmpty();
    }

    public boolean hasDistanceSensors() {
        return !distanceSensors.isEmpty();
    }

    @Override
    public String toString() {
        return "RoloTheRobot{" + "name=" + name + ", motors=" + motors + ", distanceSensors=" + distanceSensors + ", tiltSensors=" + tiltSensors + '}';
    }
}
