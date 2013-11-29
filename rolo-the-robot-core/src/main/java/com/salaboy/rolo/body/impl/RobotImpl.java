/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.salaboy.rolo.body.impl;

import com.salaboy.rolo.body.api.Robot;
import com.salaboy.rolo.body.api.RobotPart;
import com.salaboy.rolo.events.RobotPartAddedEvent;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.event.Event;
import javax.inject.Inject;

/**
 *
 * @author salaboy
 */
public class RobotImpl implements Robot{
    private String name;
    private int x;
    private int y;
    private int z;
    private int orientation;
    
    private final List<RobotPart> parts = new ArrayList<RobotPart>();
    
    @Inject
    private Event<RobotPartAddedEvent> robotPartAddedEvent;

    public RobotImpl() {
        this.name = "robot";
    }

    public RobotImpl(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int getPositionX() {
        return x;
    }

    @Override
    public int getPositionY() {
        return y;
    }

    @Override
    public int getPositionZ() {
        return z;
    }

    @Override
    public int getOrientation() {
        return orientation;
    }

    @Override
    public void setPositionX(int x) {
        this.x = x;
    }

    @Override
    public void setPositionY(int y) {
        this.y = y;
    }

    @Override
    public void setPositionZ(int z) {
        this.z = z;
    }

    @Override
    public void setOrientation(int orientation) {
        this.orientation = orientation;
    }

    @Override
    public void addRobotPart(RobotPart part) {
        if(robotPartAddedEvent != null){
            robotPartAddedEvent.fire(new RobotPartAddedEvent(part));
        }
        parts.add(part);
    }

    @Override
    public List<RobotPart> getRobotParts() {
        return parts;
    }

    @Override
    public String toString() {
        return "RobotImpl{" + "name=" + name + ", x=" + x + ", y=" + y + ", z=" + z + ", orientation=" + orientation + ", parts=" + parts + '}';
    }

}
