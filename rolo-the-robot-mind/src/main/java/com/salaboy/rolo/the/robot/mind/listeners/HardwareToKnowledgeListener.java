/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.salaboy.rolo.the.robot.mind.listeners;

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

    public HardwareToKnowledgeListener() {
        System.out.println(" >>> ROLO HARDWARE TO KNOWLEDGE LISTENER CREATED! " +this.hashCode());
    }
    
    
    
    public void onHardwareEvent(@Observes HardwareNotificationEvent event){
            String[] data = event.getData().split(":");
            if(data.length > 0){
                if(data[0].equalsIgnoreCase("sonar-report")){
                    sonarEvent.fire(new ReadDistanceSensorEvent(data[1], Integer.valueOf(data[2])));
                }
            }
    }
}
