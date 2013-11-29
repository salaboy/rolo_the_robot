/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.salaboy.rolo.server.events;

/**
 *
 * @author salaboy
 */
public class IncomingActionEvent {
    private String[] values;

    public IncomingActionEvent(String[] values) {
        this.values = values;
    }

    public IncomingActionEvent() {
    }

    public String[] getValues() {
        return values;
    }

    public void setValues(String[] values) {
        this.values = values;
    }

    @Override
    public String toString() {
        return "IncomingActionEvent{" + "values=" + values + '}';
    }
    
    
}
