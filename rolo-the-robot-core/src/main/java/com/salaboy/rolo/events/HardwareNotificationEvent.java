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
public class HardwareNotificationEvent {
    private String data;

    public HardwareNotificationEvent(String data) {
        this.data = data;
    }

    public HardwareNotificationEvent() {
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
    
}
