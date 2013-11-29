/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.salaboy.rolo.events;

/**
 *
 * @author salaboy
 */
public class BodyEvent {
    private String payload;

    public BodyEvent() {
    }

    public BodyEvent(String payload) {
        this.payload = payload;
    }
    
    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }
    
    
    
}
