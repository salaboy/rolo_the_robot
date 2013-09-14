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
public class Wall implements WorldShape {
    private int x;
    private int y;
    private int z;
    private int orientation;
    private String name;

    public Wall() {
    }

    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

   

    @Override
    public String toString() {
        return "Wall{" + "x=" + x + ", y=" + y + ", name=" + name + '}';
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
