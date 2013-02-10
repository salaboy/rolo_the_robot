/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.salaboy.rolo.arduino.behavior;

import com.salaboy.rolo.api.Motor;
import com.salaboy.rolo.arduino.Arduino;
import com.salaboy.rolo.arduino.ArduinoFirmata;
import com.salaboy.rolo.arduino.ArduinoMotor;
import com.salaboy.rolo.arduino.ArduinoMotorImpl;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author salaboy
 */
@Arduino
public class RoloTractionMotorPairImpl implements RoloTractionMotorPair{

  private ArduinoMotor right;
  private ArduinoMotor left;
  @Inject
  @Named("arduino")
  private ArduinoFirmata arduino;
  
  @Override
  public void setRightMotor(ArduinoMotor right) {
    this.right = right;
  }

  @Override
  public void setLeftMotor(ArduinoMotor left) {
    this.left = left;
  }

  @Override
  public void rotate(final ROTATION rotation) {
    final int rightSignal1 = right.getSignal1();
    final int rightSignal2 = right.getSignal2();
    final int rightPWI = right.getPwm();
    final int leftSignal1 = left.getSignal1();
    final int leftSignal2 = left.getSignal2();
    final int leftPWI = left.getPwm();
    final int speed = 126;
    final int duration = 500;
    new Thread(new Runnable() {
            @Override
            public void run() {
               if(rotation.equals(ROTATION.LEFT)){ 
                arduino.digitalWrite(rightSignal1, ArduinoFirmata.LOW);
                arduino.digitalWrite(rightSignal2, ArduinoFirmata.HIGH);
               
                arduino.digitalWrite(leftSignal1, ArduinoFirmata.HIGH);
                arduino.digitalWrite(leftSignal2, ArduinoFirmata.LOW);
                
               }else if(rotation.equals(ROTATION.RIGHT)) {
                arduino.digitalWrite(rightSignal1, ArduinoFirmata.HIGH);
                arduino.digitalWrite(rightSignal2, ArduinoFirmata.LOW);
               
                arduino.digitalWrite(leftSignal1, ArduinoFirmata.LOW);
                arduino.digitalWrite(leftSignal2, ArduinoFirmata.HIGH);
               }
               arduino.digitalWrite(rightPWI, speed);
               arduino.digitalWrite(leftPWI, speed);
               //@TODO: THIS SHOULD SET THE RIGHT AND LEFT MOTOR TO RUNNING WITH ITS CORRESPONDING DIRECTIONS
               
                try {
                    Thread.sleep(duration);
                } catch (InterruptedException ex) {
                    Logger.getLogger(ArduinoMotorImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
                arduino.digitalWrite(rightSignal1, ArduinoFirmata.LOW);
                arduino.digitalWrite(rightSignal2, ArduinoFirmata.LOW);
                arduino.digitalWrite(rightPWI, 0);
               
                arduino.digitalWrite(leftSignal1, ArduinoFirmata.LOW);
                arduino.digitalWrite(leftSignal2, ArduinoFirmata.LOW);
                arduino.digitalWrite(leftPWI, 0);
                
                }
        }).start();
    
  }

  @Override
  public Motor getRightMotor() {
    return this.right;
  }

  @Override
  public Motor getLeftMotor() {
    return this.left;
  }
  
}
