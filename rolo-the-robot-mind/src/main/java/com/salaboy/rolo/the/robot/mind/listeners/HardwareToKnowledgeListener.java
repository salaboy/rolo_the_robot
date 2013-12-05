/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.salaboy.rolo.the.robot.mind.listeners;

import com.salaboy.rolo.events.ExternalNotificationEvent;
import com.salaboy.rolo.events.HardwareNotificationEvent;
import com.salaboy.rolo.events.ReadSonarsEvent;
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
    private Event<ReadSonarsEvent> sonarEvent;

    @Inject
    private Event<ExternalNotificationEvent> externalEvent;
    
    public HardwareToKnowledgeListener() {
    }

    public void onHardwareEvent(@Observes HardwareNotificationEvent event){
            String[] data = event.getData().split(":");
            
            if(data.length > 0){
                System.out.println("DATA IN HARDWARE EVENT: "+data[0] + " data 1 =" + data[1]);
                if(data[0].equalsIgnoreCase("SONARS_REPORT")){
                    String reads = data[1].substring(0, data[1].length() -1);
                    String[] split = reads.split("-");
                    if(split.length == 4){
                        sonarEvent.fire(new ReadSonarsEvent(Integer.valueOf(split[0]),Integer.valueOf(split[1]),
                                                            Integer.valueOf(split[2]), Integer.valueOf(split[3]) ));
                    }
                    externalEvent.fire(new ExternalNotificationEvent(">>>SONARS = "+data[1]));
                }
            }
            
    }
}
