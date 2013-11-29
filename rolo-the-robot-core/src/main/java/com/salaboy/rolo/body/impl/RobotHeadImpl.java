/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.salaboy.rolo.body.impl;

import com.salaboy.rolo.body.api.RobotHead;
import com.salaboy.rolo.events.BodyEvent;
import javax.enterprise.event.Event;
import javax.inject.Inject;

/**
 *
 * @author salaboy
 */
public class RobotHeadImpl implements RobotHead{

    private String name;
    
    @Inject
    private Event<BodyEvent> messageEvents;

    public RobotHeadImpl() {
        this.name = "head";
    }

    public RobotHeadImpl(String name) {
        this.name = name;
    }
    
    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }
    
    @Override
    public void rotate(int degrees) {
        messageEvents.fire(new BodyEvent(name+":rotate:1:"+degrees));
    }

    @Override
    public void getAngle() {
        messageEvents.fire(new BodyEvent(name+":readAngle:0"));
    }

    @Override
    public void scan() {
        messageEvents.fire(new BodyEvent(name+":scan:0"));
    }
    
}
