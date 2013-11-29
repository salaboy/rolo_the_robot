/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.salaboy.rolo.body.api;

/**
 *
 * @author salaboy
 */
public interface RobotHead extends RobotPart {

  void rotate(int degrees);
  
  void getAngle();

  void scan();
  
 
  
}
