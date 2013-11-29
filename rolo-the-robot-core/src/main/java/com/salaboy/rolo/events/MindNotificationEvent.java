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
public class MindNotificationEvent {
    private String notification;

    public MindNotificationEvent() {
    }

    public MindNotificationEvent(String notification) {
        this.notification = notification;
    }
    
    public String getNotification() {
        return notification;
    }

    public void setNotification(String notification) {
        this.notification = notification;
    }

    @Override
    public String toString() {
        return "MindNotificationEvent{" + "notification=" + notification + '}';
    }
    
}
