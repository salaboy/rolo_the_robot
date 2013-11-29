/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.salaboy.rolo.the.robot.mind.mock;

import com.salaboy.rolo.events.HardwareNotificationEvent;
import com.salaboy.rolo.events.BodyEvent;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

/**
 *
 * @author salaboy
 */
public class HardwareMock {

    @Inject
    private Event<HardwareNotificationEvent> hardwareNotificationEvent;
    
    public void onMessageEvent(@Observes BodyEvent event){
        if(event.getPayload().contains("sonar")){
            hardwareNotificationEvent.fire(new HardwareNotificationEvent("SONAR-REPORT:sonar1:100"));
        }else if(event.getPayload().contains("motor")){
            hardwareNotificationEvent.fire(new HardwareNotificationEvent("ANGLE-REPORT:motor:360"));
        }
    }

}
