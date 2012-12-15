/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.salaboy.rolo.mock;

import com.salaboy.rolo.api.Motor;
import javax.enterprise.inject.Alternative;

/**
 *
 * @author salaboy
 */
@Alternative
public class MockMotorImpl implements Motor{

    private String name;

    public MockMotorImpl() {
    }

    public MockMotorImpl(String name) {
        this.name = name;
    }
    
    
    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void forward(int speed, long millisec) {
        
    }

    @Override
    public void backward(int speed, long millisec) {
        
    }

    @Override
    public void start(int speed, DIRECTION dir) {
        
    }

    @Override
    public void stop() {
        
    }
    
}
