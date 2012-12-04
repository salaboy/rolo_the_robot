/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.salaboy.rolo.model;

import javax.annotation.PostConstruct;

/**
 *
 * @author salaboy
 */
public class RoloTheRobot {

    private String name;
    
    private String status;
    

    @PostConstruct
    public void init(){
        // This should have the session
    }
    
    public RoloTheRobot(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "RoloTheRobot{" + "name=" + name + ", status=" + status + '}';
    }

    
 
}
