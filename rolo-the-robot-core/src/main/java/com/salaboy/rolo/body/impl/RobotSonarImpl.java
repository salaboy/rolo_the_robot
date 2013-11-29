/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.salaboy.rolo.body.impl;

import com.salaboy.rolo.body.api.RobotSonar;
import com.salaboy.rolo.events.BodyEvent;
import javax.enterprise.event.Event;
import javax.inject.Inject;

/**
 *
 * @author salaboy
 */
public class RobotSonarImpl implements RobotSonar{

    private String name;
    
    @Inject
    private Event<BodyEvent> messageEvents;

    public RobotSonarImpl() {
        this.name = "sonar";
    }

    public RobotSonarImpl(String name) {
        this.name = name;
    }
    
    @Override
    public void readDistance() {
        messageEvents.fire(new BodyEvent(name+":read:0"));
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
    public String toString() {
        return "RobotSonarImpl{" + "name=" + name + '}';
    }

    
}
