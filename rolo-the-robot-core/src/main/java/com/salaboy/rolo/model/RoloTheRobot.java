/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.salaboy.rolo.model;

/**
 *
 * @author salaboy
 */
public class RoloTheRobot implements WorldShape{
    private String name;
    private int x;
    private int y;
    private int z;
    private int orientation;
    

    public RoloTheRobot() {
    }

    public RoloTheRobot(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + (this.name != null ? this.name.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final RoloTheRobot other = (RoloTheRobot) obj;
        if ((this.name == null) ? (other.name != null) : !this.name.equals(other.name)) {
            return false;
        }
        return true;
    }

    @Override
    public int getPositionX() {
        return x;
    }

    @Override
    public int getPositionY() {
        return y;
    }

    @Override
    public int getPositionZ() {
        return z;
    }

    @Override
    public int getOrientation() {
        return orientation;
    }

    @Override
    public String toString() {
        return "RoloTheRobot{" + "name=" + name + ", x=" + x + ", y=" + y + ", z=" + z + ", orientation=" + orientation + '}';
    }

    @Override
    public void setPositionX(int x) {
        this.x = x;
    }

    @Override
    public void setPositionY(int y) {
        this.y = y;
    }

    @Override
    public void setPositionZ(int z) {
        this.z = z;
    }

    @Override
    public void setOrientation(int orientation) {
        this.orientation = orientation;
    }
    
    
    
}
