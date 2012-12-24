/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.salaboy.rolo.mock;

import com.salaboy.rolo.api.Tilt;
import com.salaboy.rolo.api.TiltSensor;
import javax.enterprise.inject.Alternative;

/**
 *
 * @author salaboy
 */
@Alternative
public class MockTiltSensorImpl implements TiltSensor {

    private String name;
    
    @Override
    public Tilt readTilt() {
        return null;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
    
}
