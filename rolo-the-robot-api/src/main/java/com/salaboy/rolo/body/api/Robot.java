/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.salaboy.rolo.body.api;

import com.salaboy.rolo.world.api.WorldObject;
import java.util.List;

/**
 *
 * @author salaboy
 */
public interface Robot extends WorldObject{
    String getName();
    void setName(String name);
    
    void addRobotPart(RobotPart part);
    List<RobotPart> getRobotParts();
}
