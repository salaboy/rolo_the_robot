/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.salaboy.rolo.body.impl;

import com.salaboy.rolo.body.api.RobotMotor;
import com.salaboy.rolo.events.BodyEvent;
import javax.enterprise.event.Event;
import javax.inject.Inject;

/**
 *
 * @author salaboy
 */
public class RobotMotorImpl implements RobotMotor {

    private String name;
    
    @Inject
    private Event<BodyEvent> messageEvents;

    public RobotMotorImpl(String name) {
        this.name = name;
    }

    public RobotMotorImpl() {
        this.name = "motor";
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
    public void forward(int speed, long millisec) {
        messageEvents.fire(new BodyEvent(name+":start:4:forward:"+speed+":90:brake"));
    }

    @Override
    public void backward(int speed, long millisec) {
        messageEvents.fire(new BodyEvent(name+":start:4:backward:"+speed+":90:brake"));
    }

    @Override
    public void stop() {
        messageEvents.fire(new BodyEvent(name+":stop:0"));
    }


    @Override
    public void forward() {
        messageEvents.fire(new BodyEvent(name+":forward:0"));
    }

    @Override
    public void backward() {
        messageEvents.fire(new BodyEvent(name+":backward:0"));
    }

    @Override
    public void setSpeed(int speed) {
        messageEvents.fire(new BodyEvent(name+":setSpeed:1:"+speed));
    }

    @Override
    public void getSpeed() {
        messageEvents.fire(new BodyEvent(name+":getSpeed:0"));
    }

    @Override
    public void isTurning() {
        messageEvents.fire(new BodyEvent(name+":isturning:0"));
    }

    @Override
    public void getAngle() {
        messageEvents.fire(new BodyEvent(name+":read:0"));
    }

    public void resetAngle() {
        messageEvents.fire(new BodyEvent(name+":reset:0"));
    }

    @Override
    public void rotate(int degrees, String direction, String brake) {
        messageEvents.fire(new BodyEvent(name+":rotate:3:"+degrees+":"+direction+":"+brake));
    }
    
}
