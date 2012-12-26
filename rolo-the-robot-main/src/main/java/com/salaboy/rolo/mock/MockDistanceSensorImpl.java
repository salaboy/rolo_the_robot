/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.salaboy.rolo.mock;

import com.salaboy.rolo.api.DistanceSensor;

/**
 *
 * @author salaboy
 */
@Mock
public class MockDistanceSensorImpl implements DistanceSensor {

    private String name;
    
    @Override
    public int readDistance() {
        return 0;
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
