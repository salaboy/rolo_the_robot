/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.salaboy.rolo.wedo.impl;

import com.salaboy.rolo.wedo.api.BlockManager;
import com.salaboy.rolo.wedo.api.Tilt;
import com.salaboy.rolo.wedo.api.TiltSensor;
import javax.inject.Inject;

/**
 *
 * @author salaboy
 */
public class SimpleTiltSensorImpl implements TiltSensor {

    @Inject
    private BlockManager manager;

    /*
     * 
     */
    public Tilt readTilt() {
        byte[] buff = new byte[8];

        int n = manager.read(buff);
        if (n != 8) {
            throw new IllegalStateException(" Wrong data");
        }
        int tilt = -1;
        
        //System.out.println("## " + (buff[0] & 0xff) + "," + (buff[1] & 0xff) + "," + (buff[2] & 0xff) + "," + (buff[3] & 0xff) + "," + (buff[4] & 0xff) + "," + (buff[5] & 0xff) + "," + (buff[6] & 0xff) + "," + (buff[7] & 0xff));
        if ((buff[3] & 0xff) == 38 || (buff[3] & 0xff) == 39 ) {
            tilt = (buff[2] & 0xff);
            
        }

       
        if(tilt > 10 && tilt < 40 ){
            return Tilt.BACK;
        }
        if(tilt > 60 && tilt < 90 ){
            return Tilt.RIGHT;
        }
        
        if(tilt > 170 && tilt < 190 ){
            return Tilt.FORWARD;
        }
        if(tilt > 220 && tilt < 240 ){
            return Tilt.LEFT;
        }
        if(tilt > 120 && tilt < 140 ){
            return Tilt.NO_TILT;
        }
        
        return Tilt.NO_TILT;

    }
}
