/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.salaboy.rolo.body.api;

/**
 *
 * @author salaboy
 */
public interface RobotMotor extends RobotPart {
  
    void forward();
    
    void backward();
    
    void setSpeed(int speed);
    
    void getSpeed();
    
    void getAngle();
    
    void rotate(int degrees,String direction, String brake);
    
    void stop();
    
    void isTurning();
   
}
