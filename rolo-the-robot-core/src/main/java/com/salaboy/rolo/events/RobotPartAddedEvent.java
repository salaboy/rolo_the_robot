/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.salaboy.rolo.events;

import com.salaboy.rolo.body.api.RobotPart;

/**
 *
 * @author salaboy
 */
public class RobotPartAddedEvent {
    private RobotPart robotPart;
    
    
    public RobotPartAddedEvent() {
    
    }

    public RobotPartAddedEvent(RobotPart robotPart) {
        this.robotPart = robotPart;
    }
    
    public RobotPart getRobotPart() {
        return robotPart;
    }

    public void setRobotPart(RobotPart robotPart) {
        this.robotPart = robotPart;
    }
    
    
    
}
