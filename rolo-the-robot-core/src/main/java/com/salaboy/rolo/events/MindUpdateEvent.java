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
public class MindUpdateEvent {
    private String content;

    public MindUpdateEvent(String content) {
        this.content = content;
    }

    public MindUpdateEvent() {
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "MindUpdateEvent{" + "content=" + content + '}';
    }
    
    
}
