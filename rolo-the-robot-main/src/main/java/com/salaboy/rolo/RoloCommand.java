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

    public RoloCommand(String cmd) {
        this.cmd = cmd;
    }

    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    @Override
    public String toString() {
        return "RoloCommand{" + "cmd=" + cmd + '}';
    }
    
    
}
