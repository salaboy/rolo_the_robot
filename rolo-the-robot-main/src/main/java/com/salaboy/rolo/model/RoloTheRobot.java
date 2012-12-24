/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.salaboy.rolo.model;

import javax.annotation.PostConstruct;

/**
 *
 * @author salaboy
 */
public class RoloTheRobot {

    private String name;
    private String status = "alive!";
    private int positionX = 0;
    private int positionY = 0;
    private int orientation = 0; // 0 - 360

    @PostConstruct
    public void init() {
    }

    public RoloTheRobot(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;

    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;

    }

    public int getOrientation() {
        return orientation;
    }

    public void setOrientation(int orientation) {
        this.orientation = orientation;

    }

    @Override
    public String toString() {
        return "RoloTheRobot{" + "name=" + name + ", status=" + status + '}';
    }
}
