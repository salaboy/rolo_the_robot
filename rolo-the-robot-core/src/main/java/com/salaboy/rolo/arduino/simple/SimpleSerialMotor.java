/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.salaboy.rolo.arduino.simple;

import com.salaboy.rolo.api.Motor;
import com.salaboy.rolo.the.robot.comm.HornetQSessionWriter;
import javax.inject.Inject;

/**
 *
 * @author salaboy
 */
public class SimpleSerialMotor implements Motor, EmitsNotification {

    private String name;
    
    @Inject
    private RoloFeatureSerial roloSerial;
    
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
        roloSerial.sendMessage(name+":start:4:forward:"+speed+":90:brake");
    }

    @Override
    public void backward(int speed, long millisec) {
        roloSerial.sendMessage(name+":start:4:backward:"+speed+":90:brake");
    }

    @Override
    public void start(int speed, DIRECTION dir) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void stop() {
        roloSerial.sendMessage(name+":stop:0");
    }

    @Override
    public boolean isRunning() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setRunning(boolean running) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DIRECTION getCurrentDirection() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void forward() {
        roloSerial.sendMessage(name+":forward:0");
    }

    @Override
    public void backward() {
        roloSerial.sendMessage(name+":backward:0");
    }

    @Override
    public void setSpeed(int speed) {
        roloSerial.sendMessage(name+":setSpeed:1:"+speed);
    }

    @Override
    public void getSpeed() {
        roloSerial.sendMessage(name+":getSpeed:0");
    }

    @Override
    public void setNotifications(HornetQSessionWriter notifications) {
        roloSerial.setNotifications(notifications);
    }

    @Override
    public void isTurning() {
        roloSerial.sendMessage(name+":isturning:0");
    }

    @Override
    public void getAngle() {
        roloSerial.sendMessage(name+":read:0");
    }

    public void resetAngle() {
        roloSerial.sendMessage(name+":reset:0");
    }

    @Override
    public void rotate(int degrees, String direction, String brake) {
        roloSerial.sendMessage(name+":rotate:3:"+degrees+":"+direction+":"+brake);
    }
    
}
