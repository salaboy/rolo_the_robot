/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.salaboy.rolo.body.impl;

import com.salaboy.rolo.body.api.RobotSonars;
import com.salaboy.rolo.events.BodyEvent;
import javax.annotation.PostConstruct;
import javax.enterprise.event.Event;
import javax.inject.Inject;

/**
 *
 * @author salaboy
 */
public class RobotSonarsImpl implements RobotSonars {
    private String name;
    
   
    
    @Inject
    private Event<BodyEvent> messageEvents;

    public RobotSonarsImpl() {
        this.name = "sonars";
        
    }
    @PostConstruct
    public void init(){
        
    }
    
    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

   
    @Override
    public void readAll(){
         messageEvents.fire(new BodyEvent(name+":read:0"));
    }

    
    
    
}
