/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.salaboy.rolo.the.robot.mind.listeners;

import com.salaboy.rolo.events.ExternalNotificationEvent;
import com.salaboy.rolo.events.HardwareNotificationEvent;
import com.salaboy.rolo.events.ReadDistanceSensorEvent;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 *
 * @author salaboy
 */
@Singleton
public class HardwareToKnowledgeListener {
    
    @Inject
    private Event<ReadDistanceSensorEvent> sonarEvent;

    @Inject
    private Event<ExternalNotificationEvent> externalEvent;
    
    public HardwareToKnowledgeListener() {
    }
    
    
    
    public void onHardwareEvent(@Observes HardwareNotificationEvent event){
            String[] data = event.getData().split(":");
            
            if(data.length > 0){
                System.out.println("DATA IN HARDWARE EVENT: "+data[0] + " data 1 =" + data[1]);
                if(data[0].equalsIgnoreCase("SONAR_FRONT_REPORT")){
                    sonarEvent.fire(new ReadDistanceSensorEvent("SONAR_FRONT", Integer.valueOf(data[1].substring(0, data[1].length() -1))));
                    externalEvent.fire(new ExternalNotificationEvent(">>>SONAR_FRONT = "+data[1]));
                }else if(data[0].equalsIgnoreCase("SONAR_BACK_REPORT")){
                    sonarEvent.fire(new ReadDistanceSensorEvent("SONAR_BACK", Integer.valueOf(data[1].substring(0, data[1].length() -1))));
                    externalEvent.fire(new ExternalNotificationEvent(">>>SONAR_BACK = "+data[1]));
                }else if(data[0].equalsIgnoreCase("SONAR_RIGHT_REPORT")){
                    sonarEvent.fire(new ReadDistanceSensorEvent("SONAR_RIGHT", Integer.valueOf(data[1].substring(0, data[1].length() -1))));
                    externalEvent.fire(new ExternalNotificationEvent(">>>SONAR_RIGHT = "+data[1]));
                }else if(data[0].equalsIgnoreCase("SONAR_LEFT_REPORT")){
                    sonarEvent.fire(new ReadDistanceSensorEvent("SONAR_LEFT", Integer.valueOf(data[1].substring(0, data[1].length() -1))));
                    externalEvent.fire(new ExternalNotificationEvent(">>>SONAR_LEFT = "+data[1]));
                }
            }
            
    }
}
