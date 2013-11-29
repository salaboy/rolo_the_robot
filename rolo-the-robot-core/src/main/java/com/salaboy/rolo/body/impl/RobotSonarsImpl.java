/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.salaboy.rolo.body.impl;

import com.salaboy.rolo.body.api.RobotSonar;
import com.salaboy.rolo.body.api.RobotSonars;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

/**
 *
 * @author salaboy
 */
public class RobotSonarsImpl implements RobotSonars {
    private String name;
    
    @Inject
    private RobotSonar frontSonar;
    @Inject
    private RobotSonar backSonar;
    @Inject
    private RobotSonar leftSonar;
    @Inject
    private RobotSonar rightSonar;

    public RobotSonarsImpl() {
        this.name = "sonars";
        
    }
    @PostConstruct
    public void init(){
        frontSonar.setName("sonar-front");
        backSonar.setName("sonar-back");
        leftSonar.setName("sonar-left");
        rightSonar.setName("sonar-right");
    }
    
    @Override
    public List<RobotSonar> getSonars() {
        List<RobotSonar> sonars = new ArrayList<RobotSonar>(4);
        sonars.add(frontSonar);
        sonars.add(backSonar);
        sonars.add(rightSonar);
        sonars.add(leftSonar);
        return sonars;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public RobotSonar getFrontSonar() {
        return frontSonar;
    }

    public RobotSonar getBackSonar() {
        return backSonar;
    }

    public RobotSonar getLeftSonar() {
        return leftSonar;
    }

    public RobotSonar getRightSonar() {
        return rightSonar;
    }
    
    
    
}
