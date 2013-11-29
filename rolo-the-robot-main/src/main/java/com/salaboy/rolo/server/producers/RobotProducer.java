/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.salaboy.rolo.server.producers;

import com.salaboy.rolo.body.api.Robot;
import com.salaboy.rolo.body.api.RobotFrontWheels;
import com.salaboy.rolo.body.api.RobotSonars;
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
    
    public void onIncomingActionEvent(@Observes IncomingActionEvent event){
        // Parse incoming action and delegate to correspndant body part
        String[] values = event.getValues();
        
        
        System.out.println(">>> Parsing Incomming Action Request");
        for(String s : values){
            System.out.println(">>> value: "+ s);
        }
        System.out.println("------------------------------------");
    }
    
    
}
