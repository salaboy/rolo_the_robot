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
public class ExternalNotificationEvent {
    private String payload;

    public ExternalNotificationEvent(String payload) {
        this.payload = payload;
    }

    public ExternalNotificationEvent() {
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    @Override
    public String toString() {
        return "ExternalNotificationEvent{" + "payload=" + payload + '}';
    }
    
    
}
