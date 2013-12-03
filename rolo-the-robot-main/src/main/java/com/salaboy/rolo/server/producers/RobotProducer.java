/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.salaboy.rolo.server.producers;

import com.salaboy.rolo.body.api.Robot;
import com.salaboy.rolo.body.api.RobotFrontWheels;
import com.salaboy.rolo.body.api.RobotSonars;
import com.salaboy.rolo.events.MindNotificationEvent;
import com.salaboy.rolo.server.CompleteRobot;
import com.salaboy.rolo.server.events.IncomingActionEvent;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;


/**
 *
 * @author salaboy
 */
public class RobotProducer {
    
    @Inject
    private Robot robot;
      
    @Inject
    private RobotFrontWheels roloFrontWheels;

    @Inject
    private RobotSonars roloSonars;

    
    @Produces
    @CompleteRobot
    public Robot getRobot(){
        robot.addRobotPart(roloFrontWheels);
        robot.addRobotPart(roloFrontWheels.getLeftMotor());
        robot.addRobotPart(roloFrontWheels.getRightMotor());
        robot.addRobotPart(roloSonars);
        robot.addRobotPart(roloSonars.getFrontSonar());
        robot.addRobotPart(roloSonars.getBackSonar());
        robot.addRobotPart(roloSonars.getRightSonar());
        robot.addRobotPart(roloSonars.getLeftSonar());
        return robot;
    }
    
    public void onMindNotification(@Observes MindNotificationEvent event){
        System.out.println(">> I'm getting a mind suggestion: "+ event);
    }
    
    public void onIncomingActionEvent(@Observes IncomingActionEvent event){
        // Parse incoming action and delegate to correspndant body part
        String[] values = event.getValues();
//        System.out.println(">>> Parsing Incomming Action Request");
//        for(String s : values){
//            System.out.println(">>> value: "+ s);
//        }
//        System.out.println("------------------------------------");
        if(values[0].equals("WHEELS-FORWARD")){
            roloFrontWheels.move("forward", Integer.valueOf(values[1]));
        }else if(values[0].equals("WHEELS-BACKWARD")){
            roloFrontWheels.move("backward", Integer.valueOf(values[1]));
        }else if(values[0].equals("WHEELS-ROTATERIGHT")){
            roloFrontWheels.rotate("right", Integer.valueOf(values[1]));
        }else if(values[0].equals("WHEELS-ROTATELEFT")){
            roloFrontWheels.rotate("left", Integer.valueOf(values[1]));
        }else if(values[0].equals("WHEEL-RIGHT-FORWARD")){
            roloFrontWheels.getRightMotor().forward();
        }else if(values[0].equals("WHEEL-LEFT-FORWARD")){
            roloFrontWheels.getLeftMotor().forward();
        }else if(values[0].equals("WHEEL-RIGHT-BACKWARD")){
            roloFrontWheels.getRightMotor().backward();
        }else if(values[0].equals("WHEEL-LEFT-BACKWARD")){
            roloFrontWheels.getLeftMotor().backward();
        }else if(values[0].equals("SONAR-FRONT")){
            roloSonars.getFrontSonar().readDistance();
        }else if(values[0].equals("SONAR-BACK")){
            roloSonars.getBackSonar().readDistance();
        }else if(values[0].equals("SONAR-LEFT")){
            roloSonars.getLeftSonar().readDistance();
        }else if(values[0].equals("SONAR-RIGHT")){
            roloSonars.getRightSonar().readDistance();
        }else if(values[0].equals("STOP-ALL")){
            roloFrontWheels.stopAll();
        }
        
       
    }
    
    
}
