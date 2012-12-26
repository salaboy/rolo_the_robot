/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.salaboy.rolo;

import java.io.Serializable;

/**
 *
 * @author salaboy
 */
public class RoloCommand implements Serializable{
    private String cmd;
    private String value;
    
    public RoloCommand(String cmd, String value) {
        this.cmd = cmd;
        this.value = value;
    }
    
    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "RoloCommand{" + "cmd=" + cmd + ", value=" + value + '}';
    }

   
    
    
}
